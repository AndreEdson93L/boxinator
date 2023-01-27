package no.accelerate.springwebpreswagger.repositories;

import no.accelerate.springwebpreswagger.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
