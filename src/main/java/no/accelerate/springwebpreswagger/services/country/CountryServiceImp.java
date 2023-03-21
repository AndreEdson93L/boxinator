package no.accelerate.springwebpreswagger.services.country;

import no.accelerate.springwebpreswagger.exceptions.CountryNotFoundException;
import no.accelerate.springwebpreswagger.mappers.CountryMapper;
import no.accelerate.springwebpreswagger.models.Country;
import no.accelerate.springwebpreswagger.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImp implements CountryService{
    private final CountryRepository countryRepository;
    @Autowired
    public CountryServiceImp(CountryRepository countryRepository, CountryMapper countryMapper){

        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country add(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public Optional<Country> findById(int id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Integer integer) {
        return null;
    }
    @Override
    public Country update(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        countryRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return countryRepository.existsById(id);
    }
}
