package no.accelerate.springwebpreswagger.exceptions;

public class CountryNotFoundException extends EntityNotFoundException{
    public CountryNotFoundException(Integer id) {
        super("Country not found with ID: " + id);
    }
}
