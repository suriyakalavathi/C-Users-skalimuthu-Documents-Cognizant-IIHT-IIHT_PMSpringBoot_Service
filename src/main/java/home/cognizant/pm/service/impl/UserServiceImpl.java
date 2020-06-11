package home.cognizant.pm.service.impl;

import home.cognizant.pm.api.exception.UserException;
import home.cognizant.pm.service.api.UserService;
import home.cognizant.pm.service.entity.UserObject;
import home.cognizant.pm.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserObject add(UserObject user) {    
        return userRepository.save(user);
    }

    @Override
    public List<UserObject> edit(UserObject user) {     
        Set<UserObject> editedUsers = getAll().stream().filter(user1 -> user1.getEmployeeId() == user.getEmployeeId())
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    return user1;
                }).collect(Collectors.toSet());
        return userRepository.saveAll(editedUsers);
    }

    @Override
    public void delete(long userId) {       
        userRepository.deleteByEmployeeId(this.get(userId).getEmployeeId());
    }

    @Override
    public UserObject get(long userId) {     
        return userRepository.findById(userId).orElseThrow(() -> new UserException(String.format("User not found for UserID \"%s\"", userId)));
    }

    @Override
    public Set<UserObject> getManagers(long projectId) {     
        return userRepository.findUsersByProject_ProjectId(projectId).stream().filter(Optional::isPresent).map(Optional::get).filter(user -> user.getTask() == null).collect(Collectors.toSet());
    }

    @Override
    public Set<UserObject> getAllWithUniqueEmployeeId() {
        Set<UserObject> users = userRepository.findUsersWithUniqueEmployeeId();      
        return users;
    }

    @Override
    public List<UserObject> getAll() {
        List<UserObject> users = userRepository.findAll();
        //log.debug("Get all users: {}", users);
        return users;
    }
}
