package no.accelerate.springwebpreswagger.models.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import javax.management.relation.Role;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    @Email
    private  String email;
    @NotBlank
    private String password;
}
