package home.cognizant.pm.api.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import home.cognizant.pm.api.model.TaskRequest;
import home.cognizant.pm.api.model.TaskResponse;
import home.cognizant.pm.service.api.ParestTaskService;
import home.cognizant.pm.service.api.ProjectService;
import home.cognizant.pm.service.entity.TaskObject;

@Mapper(componentModel = "spring", uses = { ParestTaskService.class, ProjectService.class })
public interface TaskMapper {

    @Mapping(target = "parentTask", source = "parentTaskId", qualifiedByName = {"ParentTaskService", "findParentTaskById"})
    @Mapping(target = "project", source = "projectId", qualifiedByName = {"ProjectService", "findProjectById"})
    TaskObject toTask(TaskRequest taskRequest);

    @Mapping(target = "projectId", source = "project.projectId")
    @Mapping(target = "parentTaskId", source = "parentTask.parentTaskId")
  //  @Mapping(target = "parentTaskId", source = "parentTask.null")
    TaskResponse toTaskResponse(TaskObject task);

    List<TaskResponse> toTaskResponse(Set<TaskObject> tasks);

    List<TaskResponse> toTaskResponse(List<TaskObject> tasks);
}
