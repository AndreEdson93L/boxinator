package no.accelerate.springwebpreswagger.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private Set<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private Set<Subject> subjects;
}
