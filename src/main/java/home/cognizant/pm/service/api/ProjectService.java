package home.cognizant.pm.service.api;

import java.util.List;

import org.mapstruct.Named;

import home.cognizant.pm.service.entity.ProjectObject;

@Named("ProjectService")
public interface ProjectService {
    public ProjectObject add(ProjectObject project);

    public ProjectObject edit(ProjectObject project);

    public void delete(long projectId);

    @Named("findProjectById")
    public ProjectObject get(long projectId);

    public List<ProjectObject> getAll();
}
