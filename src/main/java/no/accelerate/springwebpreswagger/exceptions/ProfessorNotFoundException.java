package no.accelerate.springwebpreswagger.exceptions;

public class ProfessorNotFoundException extends EntityNotFoundException{
    public ProfessorNotFoundException(int id) {
        super("Professor does not exist with ID: " + id);
    }
}
