package no.accelerate.springwebpreswagger.exceptions;

public class SubjectNotFoundException extends EntityNotFoundException{
    public SubjectNotFoundException(int id) {
        super("Subject does not exist with ID: " + id);
    }
}
