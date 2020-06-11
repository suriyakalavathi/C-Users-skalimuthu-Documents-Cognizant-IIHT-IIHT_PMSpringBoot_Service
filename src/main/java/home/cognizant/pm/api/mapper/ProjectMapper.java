package home.cognizant.pm.api.mapper;

import home.cognizant.pm.api.model.ProjectRequest;
import home.cognizant.pm.api.model.ProjectResponse;
import home.cognizant.pm.service.entity.ProjectObject;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectObject toProject(ProjectRequest projectRequest);

    ProjectResponse toProjectResponse(ProjectObject project);

    List<ProjectResponse> toProjectResponse(List<ProjectObject> projects);
}
