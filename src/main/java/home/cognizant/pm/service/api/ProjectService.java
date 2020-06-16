package home.cognizant.pm.service.api;

import java.util.List;

import org.mapstruct.Named;

import home.cognizant.pm.service.entity.Project;

@Named("ProjectService")
public interface ProjectService {
    public Project add(Project project);

    public Project edit(Project project);

    public void delete(long projectId);

    @Named("findProjectById")
    public Project get(long projectId);

    public List<Project> getAll();
}
