package no.accelerate.springwebpreswagger.controllers;

import jakarta.servlet.http.HttpSession;
import no.accelerate.springwebpreswagger.mappers.CountryMapper;
import no.accelerate.springwebpreswagger.models.Country;
import no.accelerate.springwebpreswagger.models.dto.country.CountryDTO;
import no.accelerate.springwebpreswagger.services.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/settings/countries")
@CrossOrigin("http://localhost:4200")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    @Autowired
    public CountryController(CountryService countryService, CountryMapper countryMapper){
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }
    @GetMapping
    public ResponseEntity<Collection<Country>> getAllCountries(HttpSession session){
        return ResponseEntity.ok(countryService.findAll());
    }
    @PostMapping()
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.add(country));
    }
    @PutMapping("/{countryId}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable int countryId, @RequestBody CountryDTO updatedCountry) {
        return countryService.findById(countryId)
                .map(country -> {
                    country.setName(updatedCountry.getName());
                    country.setMultiplier(updatedCountry.getMultiplier());
                    countryService.save(country);
                    return ResponseEntity.ok(countryMapper.convertCountryToCountryDto(country));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
