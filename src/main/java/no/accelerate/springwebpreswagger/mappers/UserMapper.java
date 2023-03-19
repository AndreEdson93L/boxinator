package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "salt", ignore = true),
            @Mapping(target = "admin", ignore = true)
    })
    User convertDtoToUser(RegistrationDTO registrationDTO);
}

