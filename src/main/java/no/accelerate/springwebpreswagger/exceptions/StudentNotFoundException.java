package no.accelerate.springwebpreswagger.exceptions;

public class StudentNotFoundException extends EntityNotFoundException{
    public StudentNotFoundException(int id) {
        super("Student does not exist with ID: " + id);
    }
}
