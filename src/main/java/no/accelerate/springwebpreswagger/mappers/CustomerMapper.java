package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    CustomerDTO mapUserToCustomerDTO(User user);
}
