package no.accelerate.springwebpreswagger.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;

    @OneToOne(mappedBy = "student")
    private Project project;

    @JsonGetter("project")
    public Integer projectGetter() {
        if(project == null)
            return null;
        return project.getId();
    }

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @JsonGetter("professor")
    public String professorGetter() {
        if(professor == null)
            return null;
        return "api/v1/professors/" + professor.getId();
    }

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private Set<Subject> subjects;

    @JsonGetter("subjects")
    public List<String> subjectsGetter() {
        if(subjects == null)
            return null;
        return subjects.stream()
                .map(s -> "api/v1/subjects/" + s.getId())
                .collect(Collectors.toList());
    }
}

