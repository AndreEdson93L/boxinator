package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.Student;
import no.accelerate.springwebpreswagger.models.Subject;
import no.accelerate.springwebpreswagger.models.dto.student.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "project", source = "project.id")
    @Mapping(target = "professor", source = "professor.id")
    @Mapping(target = "subjects", qualifiedByName = "subjectsToSubjectsId")
    StudentDTO studentToStudentDTO(Student student);

    Collection<StudentDTO> studentToStudentDTO(Collection<Student> student);

    @Named(value = "subjectsToSubjectsId")
    default Set<Integer> map(Set<Subject> value) {
        if(value == null)
            return null;
        return value.stream()
                .map(s -> s.getId())
                .collect(Collectors.toSet());
    }
}
