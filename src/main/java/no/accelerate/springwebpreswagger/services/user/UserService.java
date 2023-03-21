package no.accelerate.springwebpreswagger.services.user;

import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.services.CrudService;

public interface UserService extends CrudService<User, Integer> {
    public User updateUser(Integer id, User updatedUser);
}
