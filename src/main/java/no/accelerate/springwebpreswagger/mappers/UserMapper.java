package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.models.dto.user.RegistrationDTO;
import no.accelerate.springwebpreswagger.models.dto.user.UserDTO;
import no.accelerate.springwebpreswagger.models.dto.user.UserPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "salt", ignore = true),
            @Mapping(target = "admin", ignore = true)
    })
    User convertDtoToUser(RegistrationDTO registrationDTO);
    @Mapping(target = "id", source = "id")
    UserDTO convertUserToUserDTO(User user);
    @Mapping(target = "id", source = "id")
    User convertUserPostDtoToUser(UserPostDTO userPostDTO);

    @Mapping(target = "id", source = "id")
    UserPostDTO convertUserToUserPostDto(User user);

    @Mappings({
            @Mapping(target = "firstName", source = "userPostDTO.firstName"),
            @Mapping(target = "lastName", source = "userPostDTO.lastName"),
            @Mapping(target = "email", source = "userPostDTO.email"),
            @Mapping(target = "password", source = "userPostDTO.password"),
            @Mapping(target = "dateOfBirth", source = "userPostDTO.dateOfBirth"),
            @Mapping(target = "countryOfResidence", source = "userPostDTO.countryOfResidence"),
            @Mapping(target = "postalCode", source = "userPostDTO.postalCode"),
            @Mapping(target = "contactNumber", source = "userPostDTO.contactNumber")
    })
    void updateUserFromUserPostDTO(UserPostDTO userPostDTO, @MappingTarget User user);
}

