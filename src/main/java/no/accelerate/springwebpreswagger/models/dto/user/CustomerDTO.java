package no.accelerate.springwebpreswagger.models.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
