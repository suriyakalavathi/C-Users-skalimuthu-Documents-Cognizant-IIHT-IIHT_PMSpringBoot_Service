package home.cognizant.pm.service.api;

import java.util.List;

import org.mapstruct.Named;

import home.cognizant.pm.service.entity.ParentTaskObject;

@Named("ParentTaskService")
public interface ParestTaskService {
    public ParentTaskObject add(ParentTaskObject parentTask);

    public ParentTaskObject edit(ParentTaskObject parentTask);

    public void delete(long parentTaskId);

    @Named("findParentTaskById")
    public ParentTaskObject get(long parentTaskId);

    public List<ParentTaskObject> getAll();
}
