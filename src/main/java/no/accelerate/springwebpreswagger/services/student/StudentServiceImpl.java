package no.accelerate.springwebpreswagger.services.student;

import no.accelerate.springwebpreswagger.exceptions.StudentNotFoundException;
import no.accelerate.springwebpreswagger.models.Project;
import no.accelerate.springwebpreswagger.models.Student;
import no.accelerate.springwebpreswagger.models.Subject;
import no.accelerate.springwebpreswagger.repositories.StudentRepository;
import no.accelerate.springwebpreswagger.repositories.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElseThrow(()
                -> new StudentNotFoundException(id));
    }

    @Override
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student add(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public Student update(Student entity) {
        return studentRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Collection<Student> findAllByName(String name) {
        return studentRepository.findAllByName(name);
    }

    @Override
    public Project getProject(int studentId) {
        return studentRepository.findById(studentId).get().getProject();
    }

    @Override
    public Collection<Subject> getSubjects(int studentId) {
        return studentRepository.findById(studentId).get().getSubjects();
    }

    @Override
    public void updateSubjects(int studentId, int[] subjects) {
        Student stud = studentRepository.findById(studentId).get();
        Set<Subject> subjectList = new HashSet<>();

        for (int id: subjects) {
            subjectList.add(subjectRepository.findById(id).get());
        }
        stud.setSubjects(subjectList);
        studentRepository.save(stud);
    }
}
