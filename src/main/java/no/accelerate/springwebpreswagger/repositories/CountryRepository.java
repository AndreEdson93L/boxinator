package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
