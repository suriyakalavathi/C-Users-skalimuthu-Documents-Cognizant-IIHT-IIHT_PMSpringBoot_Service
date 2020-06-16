package home.cognizant.pm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.cognizant.pm.api.exception.TaskException;
import home.cognizant.pm.service.api.TaskService;
import home.cognizant.pm.service.entity.Task;
import home.cognizant.pm.service.entity.User;
import home.cognizant.pm.service.repository.TaskRepository;
import home.cognizant.pm.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    private void linkTaskToUser(Task task, Task persistedTask) {
        if (task.getUserId() == 0) {
            return;
        }
        User user = userRepository.findById(task.getUserId()).get();
        if (user.getTask() == null) {
            user.setTask(persistedTask);
        } else {
            // Clone the User to create a new user entry
           user.setTask(task);
       //    user.setUserId(task.getUserId());
            //user.setProject(task.getProject());
        }
        userRepository.save(user);
    }

    private void unlinkTaskFromUser(User currentUser)  {
    //    log.debug("Unlinking the current user {} from Task {}", currentUser.getUserId(), currentUser.getTask());
        currentUser.setTask(null);
        userRepository.save(currentUser);
    }

    @Override
    public Task add(Task task) {
     //   log.debug("Adding a task... {}, and to user {}", task, task.getUserId());
        Task persistedTask = taskRepository.save(task);

        linkTaskToUser(task, persistedTask);

        return persistedTask;
    }

    @Override
    public Task edit(Task task) {
       
        Task persistedTask = taskRepository.save(task);

        List<Optional<User>> optionalUser = userRepository.findUsersByTask_TaskId(persistedTask.getTaskId());
        if (!optionalUser.isEmpty()) {
            User currentUser = optionalUser.get(0).get();
            if (currentUser.getUserId() != task.getUserId()) {
                unlinkTaskFromUser(currentUser);
                linkTaskToUser(task, persistedTask);
            }
        }

        return persistedTask;
    }

    @Override
    public void delete(long taskId) {      

        List<Optional<User>> optionalUser = userRepository.findUsersByTask_TaskId(taskId);
        if (!optionalUser.isEmpty()) {
            User currentUser = userRepository.findUsersByTask_TaskId(taskId).get(0).get();
            unlinkTaskFromUser(currentUser);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task get(long projectId, long taskId) {      
        return taskRepository.findTaskByProject_ProjectIdAndTaskId(projectId, taskId).orElseThrow(() -> new TaskException(String.format("Task not found for TaskID \"%s\"", taskId)));
    }

    @Override
    public Set<Task> getAll(long projectId) {
        Set<Task> tasks = taskRepository.findTasksByProject_ProjectId(projectId);
        return tasks.stream().map(t -> {
            List<Optional<User>> optionalUser = userRepository.findUsersByTask_TaskId(t.getTaskId());
            if (!optionalUser.isEmpty()) {
                t.setUserId(optionalUser.get(0).get().getUserId());
            }
            return t;
        }).collect(Collectors.toSet());
    }
}
