package home.cognizant.pm.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import home.cognizant.pm.api.model.ParentTaskRequest;
import home.cognizant.pm.api.model.ParentTaskResponse;
import home.cognizant.pm.service.entity.ParentTask;

@Mapper(componentModel = "spring")
public interface ParentTaskMapper {
    ParentTask toParentTask(ParentTaskRequest parentTaskRequest);

    ParentTaskResponse toParentTaskResponse(ParentTask parentTask);

    List<ParentTaskResponse> toParentTaskResponse(List<ParentTask> parentTasks);
}
