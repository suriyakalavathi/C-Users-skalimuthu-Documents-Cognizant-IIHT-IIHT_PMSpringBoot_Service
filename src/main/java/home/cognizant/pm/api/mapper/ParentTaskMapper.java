package home.cognizant.pm.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import home.cognizant.pm.api.model.ParentTaskRequest;
import home.cognizant.pm.api.model.ParentTaskResponse;
import home.cognizant.pm.service.entity.ParentTaskObject;

@Mapper(componentModel = "spring")
public interface ParentTaskMapper {
    ParentTaskObject toParentTask(ParentTaskRequest parentTaskRequest);

    ParentTaskResponse toParentTaskResponse(ParentTaskObject parentTask);

    List<ParentTaskResponse> toParentTaskResponse(List<ParentTaskObject> parentTasks);
}
