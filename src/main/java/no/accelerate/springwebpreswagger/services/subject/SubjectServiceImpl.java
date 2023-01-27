package no.accelerate.springwebpreswagger.services.subject;

import no.accelerate.springwebpreswagger.models.Subject;
import no.accelerate.springwebpreswagger.repositories.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject findById(Integer id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public Collection<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject add(Subject entity) {
        return subjectRepository.save(entity);
    }

    @Override
    public Subject update(Subject entity) {
        return subjectRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public boolean exists(Integer id) {
        return subjectRepository.existsById(id);
    }
}
