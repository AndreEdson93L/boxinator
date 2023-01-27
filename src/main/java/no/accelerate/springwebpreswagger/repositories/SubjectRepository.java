package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
