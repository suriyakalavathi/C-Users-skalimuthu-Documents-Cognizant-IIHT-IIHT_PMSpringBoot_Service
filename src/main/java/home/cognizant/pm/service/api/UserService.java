package home.cognizant.pm.service.api;

import java.util.List;
import java.util.Set;

import home.cognizant.pm.service.entity.UserObject;

public interface UserService {
    public UserObject add(UserObject user);

    public List<UserObject> edit(UserObject user);

    public void delete(long userId);

    public UserObject get(long userId);

    public Set<UserObject> getManagers(long projectId);

    public Set<UserObject> getAllWithUniqueEmployeeId();

    public List<UserObject> getAll();
}
