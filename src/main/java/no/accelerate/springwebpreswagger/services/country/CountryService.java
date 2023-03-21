package no.accelerate.springwebpreswagger.services.country;

import no.accelerate.springwebpreswagger.models.Country;
import no.accelerate.springwebpreswagger.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface CountryService extends CrudService<Country, Integer> {
    List<Country> findAll();
    Optional<Country> findById(int id);
    Country save(Country country);
}
