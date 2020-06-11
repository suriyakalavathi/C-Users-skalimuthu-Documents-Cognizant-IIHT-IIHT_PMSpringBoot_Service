package home.cognizant.pm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.cognizant.pm.service.entity.ParentTaskObject;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTaskObject, Long> {
}
