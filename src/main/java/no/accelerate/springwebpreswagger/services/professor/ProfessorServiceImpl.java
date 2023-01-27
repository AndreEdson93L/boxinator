package no.accelerate.springwebpreswagger.services.professor;

import no.accelerate.springwebpreswagger.models.Professor;
import no.accelerate.springwebpreswagger.repositories.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);
    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor findById(Integer id) {
        return professorRepository.findById(id).get();
    }

    @Override
    public Collection<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor add(Professor entity) {
        return professorRepository.save(entity);
    }

    @Override
    public Professor update(Professor entity) {
        return professorRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        professorRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return professorRepository.existsById(id);
    }
}
