package no.accelerate.springwebpreswagger.config;

import jakarta.validation.Valid;
import no.accelerate.springwebpreswagger.mappers.AdminMapper;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.AdminDTO;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import no.accelerate.springwebpreswagger.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public AdminUserInitializer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createAdminUserIfNeeded(@Valid @RequestBody AdminDTO adminDTO) {
        // Convert DTO to User entity
        User admin = AdminMapper.INSTANCE.convertDtoToAdmin(adminDTO);
        admin.setAdmin(true);
        admin.setEmail("admin@email.com");
        admin.setPassword("12345678");
        admin.setContactNumber("12345678");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setDateOfBirth(LocalDate.parse("2023-03-19"));
        admin.setCountryOfResidence("Toronto");
        admin.setPostalCode("12345678");

        // Generate a salt for the new user
        String salt = Utility.generateSalt();

        // Encode the password with the salt
        admin.setPassword(Utility.hashPassword(adminDTO.getPassword(), salt));
        admin.setSalt(salt);




        System.out.println("User email: " + admin.getEmail());
        System.out.println("Stored hashed password: " + admin.getPassword());
        System.out.println("UserRepo: " + userRepository.findAll());
        userRepository.save(admin);
        System.out.println("UserRepo: " + userRepository.findAll());
        System.out.println("UserRepo: " + userRepository.findAllByEmail("admin@email.com"));

    }

    @Override
    public void run(String... args) throws Exception {
        AdminDTO adminDTO = new AdminDTO();
        createAdminUserIfNeeded(adminDTO);
    }
}
