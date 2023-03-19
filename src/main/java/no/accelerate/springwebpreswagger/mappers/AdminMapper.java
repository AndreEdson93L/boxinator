package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.AdminDTO;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "firstName", ignore = true),
            @Mapping(target = "lastName", ignore = true),
            @Mapping(target = "salt", ignore = true),
            @Mapping(target = "dateOfBirth", ignore = true),
            @Mapping(target = "countryOfResidence", ignore = true),
            @Mapping(target = "postalCode", ignore = true),
            @Mapping(target = "contactNumber", ignore = true),
    })
    User convertDtoToAdmin(AdminDTO adminDTO);
}
