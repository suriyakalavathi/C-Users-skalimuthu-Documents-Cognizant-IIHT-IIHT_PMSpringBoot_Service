package home.cognizant.pm.service.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.cognizant.pm.service.entity.TaskObject;

@Repository
public interface TaskRepository extends JpaRepository<TaskObject, Long> {
    Set<TaskObject> findTasksByParentTask_ParentTaskId(long parentTaskId);
    Set<TaskObject> findTasksByProject_ProjectId(long projectId);
    Optional<TaskObject> findTaskByProject_ProjectIdAndTaskId(long projectId, long taskId);
}
