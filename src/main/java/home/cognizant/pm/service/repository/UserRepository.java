package home.cognizant.pm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import home.cognizant.pm.service.entity.UserObject;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserObject, Long> {

    @Query(value = "SELECT pu.* FROM pm.user pu INNER JOIN (SELECT employee_id, MIN(user_id) as user_id FROM pm.user GROUP BY employee_id) AS pu2 on pu.user_id = pu2.user_id", nativeQuery = true)
    Set<UserObject> findUsersWithUniqueEmployeeId();

    List<Optional<UserObject>> findUsersByProject_ProjectId(long projectId);

    List<Optional<UserObject>> findUsersByTask_TaskId(long taskId);

    void deleteByEmployeeId(Long employeeId);
}
