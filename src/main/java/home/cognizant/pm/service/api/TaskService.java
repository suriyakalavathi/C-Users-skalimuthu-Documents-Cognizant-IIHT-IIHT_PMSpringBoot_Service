package home.cognizant.pm.service.api;

import java.util.Set;

import home.cognizant.pm.service.entity.TaskObject;

public interface TaskService {
    public TaskObject add(TaskObject task);

    public TaskObject edit(TaskObject task);

    public void delete(long taskId);

    public TaskObject get(long projectId, long taskId);

    public Set<TaskObject> getAll(long projectId);
}
