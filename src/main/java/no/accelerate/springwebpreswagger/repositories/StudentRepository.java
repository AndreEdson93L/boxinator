package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.name like %?1%")
    Set<Student> findAllByName(String name);


}
