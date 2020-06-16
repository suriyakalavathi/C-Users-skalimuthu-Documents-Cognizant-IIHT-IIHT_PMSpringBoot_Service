package home.cognizant.pm.service.api;

import java.util.Set;

import home.cognizant.pm.service.entity.Task;

public interface TaskService {
    public Task add(Task task);

    public Task edit(Task task);

    public void delete(long taskId);

    public Task get(long projectId, long taskId);

    public Set<Task> getAll(long projectId);
}
