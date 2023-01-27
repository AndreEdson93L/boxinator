package no.accelerate.springwebpreswagger.exceptions;

public class ProjectNotFoundException  extends EntityNotFoundException{
    public ProjectNotFoundException(int id) {
        super("Project does not exist with ID: " + id);
    }
}
