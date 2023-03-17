package no.accelerate.springwebpreswagger.models.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    @Email
    private  String email;
    @NotBlank
    private String password;
}