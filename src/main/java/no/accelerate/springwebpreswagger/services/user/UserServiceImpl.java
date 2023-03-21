package no.accelerate.springwebpreswagger.services.user;

import no.accelerate.springwebpreswagger.exceptions.UserNotFoundException;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import no.accelerate.springwebpreswagger.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return userRepository
                .findById(id).orElseThrow(()
                        -> new UserNotFoundException(id));
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id).map(user -> {

            user.setId(user.getId());
            user.setAdmin(user.isAdmin());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setPostalCode(updatedUser.getPostalCode());
            user.setCountryOfResidence(updatedUser.getCountryOfResidence());
            user.setContactNumber(updatedUser.getContactNumber());

            // Generate a salt for the new user
            String salt = Utility.generateSalt();
            // Encode the password with the salt
            user.setPassword(Utility.hashPassword(updatedUser.getPassword(), salt));
            user.setSalt(salt);

            return userRepository.save(user);
        }).orElse(null);
    }
}
