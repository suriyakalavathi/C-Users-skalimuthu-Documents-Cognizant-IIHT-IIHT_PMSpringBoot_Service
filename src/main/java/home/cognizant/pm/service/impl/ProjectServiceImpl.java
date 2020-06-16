package home.cognizant.pm.service.impl;

import home.cognizant.pm.api.exception.ProjectException;
import home.cognizant.pm.service.api.ProjectService;
import home.cognizant.pm.service.entity.Project;
import home.cognizant.pm.service.entity.User;
import home.cognizant.pm.service.repository.ProjectRepository;
import home.cognizant.pm.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    private void linkProjectToManager(Project project, Project persistedProject) {
        User user = userRepository.findById(project.getManagerId()).get();
        if (user.getProject() == null) {
            user.setProject(persistedProject);
        } else {
            user.setProject(project);
     //       user.setUserId(0);
          //  user.setTask(project.);
        }
        userRepository.save(user);
    }

    private void unlinkProjectFromManager(User currentManager)  {
         currentManager.setProject(null);
        userRepository.save(currentManager);
    }

    @Override
    public Project add(Project project) {
        Project persistedProject = projectRepository.save(project);

        linkProjectToManager(project, persistedProject);

        return persistedProject;
    }

    @Override
    public Project edit(Project project) {
         Project persistedProject = projectRepository.save(project);

        User currentManager = userRepository.findUsersByProject_ProjectId(persistedProject.getProjectId()).get(0).get();
        if (currentManager.getUserId() != project.getManagerId()) {
            unlinkProjectFromManager(currentManager);
            linkProjectToManager(project, persistedProject);
        }

        return persistedProject;
    }

    @Override
    public void delete(long projectId) {
        User currentManager = userRepository.findUsersByProject_ProjectId(projectId).get(0).get();
        unlinkProjectFromManager(currentManager);

        projectRepository.deleteById(projectId);
    }

    @Override
    public Project get(long projectId) {
          return projectRepository.findById(projectId).orElseThrow(() -> new ProjectException(String.format("Project not found for projectId \"%s\"", projectId)));
    }

    @Override
    public List<Project> getAll() {
          return projectRepository.findAll();
    }
}
