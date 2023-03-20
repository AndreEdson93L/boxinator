package no.accelerate.springwebpreswagger.exceptions;

public class UserNotFoundException extends EntityNotFoundException{
    public UserNotFoundException(int id) {
        super("User not found with ID: " + id);
    }
}
