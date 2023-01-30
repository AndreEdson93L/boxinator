package no.accelerate.springwebpreswagger.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.accelerate.springwebpreswagger.mappers.StudentMapper;
import no.accelerate.springwebpreswagger.models.Student;
import no.accelerate.springwebpreswagger.models.dto.student.StudentDTO;
import no.accelerate.springwebpreswagger.models.dto.student.StudentPostDTO;
import no.accelerate.springwebpreswagger.services.student.StudentService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    @Operation(summary = "Gets all the students")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class)))
                    }
            )
    })
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                studentMapper.studentToStudentDTO(
                        studentService.findAll()
                )
        );
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Adds a new student")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody StudentPostDTO entity) throws URISyntaxException {
        // Add stuent
        //studentService.add(entity);
        URI uri =  new URI("api/v1/students/" + 1);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Updates a student")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
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
