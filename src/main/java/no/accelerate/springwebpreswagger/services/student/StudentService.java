package no.accelerate.springwebpreswagger.services.student;

import no.accelerate.springwebpreswagger.models.Project;
import no.accelerate.springwebpreswagger.models.Student;
import no.accelerate.springwebpreswagger.models.Subject;
import no.accelerate.springwebpreswagger.services.CrudService;

import java.util.Collection;
import java.util.List;

public interface StudentService extends CrudService<Student, Integer> {
    Collection<Student> findAllByName(String name);
    Project getProject(int studentId);
    Collection<Subject> getSubjects(int studentId);
    void updateSubjects(int studentId, int[] subjects);
}

