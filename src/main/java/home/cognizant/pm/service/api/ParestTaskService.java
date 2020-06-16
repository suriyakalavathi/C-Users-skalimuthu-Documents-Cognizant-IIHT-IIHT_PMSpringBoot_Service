package home.cognizant.pm.service.api;

import java.util.List;

import org.mapstruct.Named;

import home.cognizant.pm.service.entity.ParentTask;

@Named("ParentTaskService")
public interface ParestTaskService {
    public ParentTask add(ParentTask parentTask);

    public ParentTask edit(ParentTask parentTask);

    public void delete(long parentTaskId);

    @Named("findParentTaskById")
    public ParentTask get(long parentTaskId);

    public List<ParentTask> getAll();
}
