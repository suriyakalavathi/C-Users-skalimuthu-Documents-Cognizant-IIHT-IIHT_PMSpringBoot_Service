package home.cognizant.pm.service.api;

import java.util.List;
import java.util.Set;

import home.cognizant.pm.service.entity.User;

public interface UserService {
    public User add(User user);

    public List<User> edit(User user);

    public void delete(long userId);

    public User get(long userId);

    public Set<User> getManagers(long projectId);

    public Set<User> getAllWithUniqueEmployeeId();

    public List<User> getAll();
}
