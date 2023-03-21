package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.Country;
import no.accelerate.springwebpreswagger.models.dto.country.CountryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "multiplier", target = "multiplier")
    CountryDTO convertCountryToCountryDto(Country country);
}
