package home.cognizant.pm.service.impl;

import home.cognizant.pm.api.exception.ProjectException;
import home.cognizant.pm.service.api.ProjectService;
import home.cognizant.pm.service.entity.ProjectObject;
import home.cognizant.pm.service.entity.UserObject;
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

    private void linkProjectToManager(ProjectObject project, ProjectObject persistedProject) {
        UserObject user = userRepository.findById(project.getManagerId()).get();
        if (user.getProject() == null) {
            user.setProject(persistedProject);
        } else {
            user = user.withProject(persistedProject);
            user.setUserId(0);
            user.setTask(null);
        }
        userRepository.save(user);
    }

    private void unlinkProjectFromManager(UserObject currentManager)  {
         currentManager.setProject(null);
        userRepository.save(currentManager);
    }

    @Override
    public ProjectObject add(ProjectObject project) {
        ProjectObject persistedProject = projectRepository.save(project);

        linkProjectToManager(project, persistedProject);

        return persistedProject;
    }

    @Override
    public ProjectObject edit(ProjectObject project) {
         ProjectObject persistedProject = projectRepository.save(project);

        UserObject currentManager = userRepository.findUsersByProject_ProjectId(persistedProject.getProjectId()).get(0).get();
        if (currentManager.getUserId() != project.getManagerId()) {
            unlinkProjectFromManager(currentManager);
            linkProjectToManager(project, persistedProject);
        }

        return persistedProject;
    }

    @Override
    public void delete(long projectId) {
        UserObject currentManager = userRepository.findUsersByProject_ProjectId(projectId).get(0).get();
        unlinkProjectFromManager(currentManager);

        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectObject get(long projectId) {
          return projectRepository.findById(projectId).orElseThrow(() -> new ProjectException(String.format("Project not found for projectId \"%s\"", projectId)));
    }

    @Override
    public List<ProjectObject> getAll() {
          return projectRepository.findAll();
    }
}
