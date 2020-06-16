package home.cognizant.pm.service.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.cognizant.pm.service.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Set<Task> findTasksByParentTask_ParentTaskId(long parentTaskId);
    Set<Task> findTasksByProject_ProjectId(long projectId);
    Optional<Task> findTaskByProject_ProjectIdAndTaskId(long projectId, long taskId);
}
