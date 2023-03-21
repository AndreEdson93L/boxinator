package no.accelerate.springwebpreswagger.models.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserPostDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String countryOfResidence;
    private String postalCode;
    private String contactNumber;
    private String password;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String salt;
    @JsonIgnore
    private boolean isAdmin;
}
