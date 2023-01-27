package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
