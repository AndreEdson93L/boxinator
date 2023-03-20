package no.accelerate.springwebpreswagger.services.user;

import no.accelerate.springwebpreswagger.exceptions.UserNotFoundException;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
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
}
