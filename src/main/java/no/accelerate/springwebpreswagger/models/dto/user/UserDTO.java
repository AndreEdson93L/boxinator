package no.accelerate.springwebpreswagger.models.dto.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String countryOfResidence;
    private String postalCode;
    private String contactNumber;
}
