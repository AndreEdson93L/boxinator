package no.accelerate.springwebpreswagger.controllers;

import no.accelerate.springwebpreswagger.mappers.ProjectMapper;
import no.accelerate.springwebpreswagger.models.Project;
import no.accelerate.springwebpreswagger.services.project.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public ResponseEntity findAll() {

        return ResponseEntity.ok(projectMapper.projectToProjectSimpleDTO(
                projectService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.ok(
                projectMapper.projectToProjectSimpleDTO(
                        projectService.findById(id)
                ));
    }
}
