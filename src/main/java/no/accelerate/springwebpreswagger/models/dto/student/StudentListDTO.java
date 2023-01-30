package no.accelerate.springwebpreswagger.models.dto.student;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentListDTO {
    private int id;
    private String name;
}
