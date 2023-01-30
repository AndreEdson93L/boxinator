package no.accelerate.springwebpreswagger.models.dto.student;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentDTO {
    private int id;
    private String name;
    private int project;
    private int professor;
    private Set<Integer> subjects;
}
