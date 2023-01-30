package no.accelerate.springwebpreswagger.mappers;

import no.accelerate.springwebpreswagger.models.Project;
import no.accelerate.springwebpreswagger.models.dto.projects.ProjectSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "description", source = "title")
    @Mapping(target = "student", source = "student.id")
    ProjectSimpleDTO projectToProjectSimpleDTO(Project project);

    Collection<ProjectSimpleDTO> projectToProjectSimpleDTO(Collection<Project> project);
}
