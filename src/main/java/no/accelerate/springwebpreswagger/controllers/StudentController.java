package no.accelerate.springwebpreswagger.controllers;

import no.accelerate.springwebpreswagger.models.Student;
import no.accelerate.springwebpreswagger.services.student.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Student entity) throws URISyntaxException {
        // Add stuent
        studentService.add(entity);
        URI uri =  new URI("api/v1/students/" + entity.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Student entity, @PathVariable int id) {
        if(id != entity.getId())
            return ResponseEntity.badRequest().build();
        studentService.update(entity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/project")
    public ResponseEntity getProject(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getProject(id));
    }

    @GetMapping("{id}/subjects")
    public ResponseEntity getSubjects(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getSubjects(id));
    }

    @PutMapping("{id}/subjects")
    public ResponseEntity updateSubjects(@PathVariable int id, @RequestBody int[] subjectIds) {
        studentService.updateSubjects(id, subjectIds);
        return ResponseEntity.noContent().build();
    }
}
