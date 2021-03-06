package home.cognizant.pm.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.cognizant.pm.service.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
