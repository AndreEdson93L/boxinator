package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.springwebpreswagger.mappers.UserMapper;
import no.accelerate.springwebpreswagger.models.dto.user.AdminDTO;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.mappers.AdminMapper;
import no.accelerate.springwebpreswagger.models.dto.user.UserPostDTO;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import no.accelerate.springwebpreswagger.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*", allowedMethods = "*")
public class AdminController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${admin.registration.secret}")
    private String adminRegistrationSecret;

    @Autowired
    public AdminController(UserRepository userRepository, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    @Operation(summary = "Register an admin.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Admin registered successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdminDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already present in the system",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdminDTO.class))
                    }
            )
    })
    public ResponseEntity<UserPostDTO> registerAdmin(@Valid @RequestBody AdminDTO adminDTO, @RequestParam("secret") String secret) {
        if (!adminRegistrationSecret.equals(secret)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        if (userRepository.findByEmail(adminDTO.getEmail()).isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        User admin = AdminMapper.INSTANCE.convertDtoToAdmin(adminDTO);
        admin.setAdmin(true);

        // Set default values
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setDateOfBirth(LocalDate.parse("2000-01-01"));
        admin.setCountryOfResidence("Unknown");
        admin.setPostalCode("Unknown");
        admin.setContactNumber("Unknown");

        String salt = Utility.generateSalt();
        admin.setPassword(Utility.hashPassword(adminDTO.getPassword(), salt));
        admin.setSalt(salt);

        userRepository.save(admin);

        return new ResponseEntity<>(userMapper.convertUserToUserPostDto(admin), HttpStatus.CREATED);
    }
}
