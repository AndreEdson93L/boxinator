package no.accelerate.springwebpreswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("api/v1/settings/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    @Autowired
    public CountryController(CountryService countryService, CountryMapper countryMapper){
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping
    @Operation(summary = "Find all countries")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Countries retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bad Request: No Country found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
    public ResponseEntity<Collection<Country>> getAllCountries(HttpSession session){
        return ResponseEntity.ok(countryService.findAll());
    }

    @PostMapping()
    @Operation(summary = "Add a new country")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Country added successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.add(country));
    }

    @PutMapping("/{countryId}")
    @Operation(summary = "Update a country by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Country updated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country non found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))
                    }
            )
    })
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
