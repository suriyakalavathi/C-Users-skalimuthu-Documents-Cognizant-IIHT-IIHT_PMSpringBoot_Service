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
import home.cognizant.pm.service.entity.TaskObject;
import home.cognizant.pm.service.entity.UserObject;
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

    private void linkTaskToUser(TaskObject task, TaskObject persistedTask) {
        if (task.getUserId() == 0) {
            return;
        }
        UserObject user = userRepository.findById(task.getUserId()).get();
        if (user.getTask() == null) {
            user.setTask(persistedTask);
        } else {
            // Clone the User to create a new user entry
     //       user = user.withTask(persistedTask);
            user.setUserId(0);
            user.setProject(null);
        }
        userRepository.save(user);
    }

    private void unlinkTaskFromUser(UserObject currentUser)  {
    //    log.debug("Unlinking the current user {} from Task {}", currentUser.getUserId(), currentUser.getTask());
        currentUser.setTask(null);
        userRepository.save(currentUser);
    }

    @Override
    public TaskObject add(TaskObject task) {
     //   log.debug("Adding a task... {}, and to user {}", task, task.getUserId());
        TaskObject persistedTask = taskRepository.save(task);

        linkTaskToUser(task, persistedTask);

        return persistedTask;
    }

    @Override
    public TaskObject edit(TaskObject task) {
       
        TaskObject persistedTask = taskRepository.save(task);

        List<Optional<UserObject>> optionalUser = userRepository.findUsersByTask_TaskId(persistedTask.getTaskId());
        if (!optionalUser.isEmpty()) {
            UserObject currentUser = optionalUser.get(0).get();
            if (currentUser.getUserId() != task.getUserId()) {
                unlinkTaskFromUser(currentUser);
                linkTaskToUser(task, persistedTask);
            }
        }

        return persistedTask;
    }

    @Override
    public void delete(long taskId) {      

        List<Optional<UserObject>> optionalUser = userRepository.findUsersByTask_TaskId(taskId);
        if (!optionalUser.isEmpty()) {
            UserObject currentUser = userRepository.findUsersByTask_TaskId(taskId).get(0).get();
            unlinkTaskFromUser(currentUser);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskObject get(long projectId, long taskId) {      
        return taskRepository.findTaskByProject_ProjectIdAndTaskId(projectId, taskId).orElseThrow(() -> new TaskException(String.format("Task not found for TaskID \"%s\"", taskId)));
    }

    @Override
    public Set<TaskObject> getAll(long projectId) {
        Set<TaskObject> tasks = taskRepository.findTasksByProject_ProjectId(projectId);
        return tasks.stream().map(t -> {
            List<Optional<UserObject>> optionalUser = userRepository.findUsersByTask_TaskId(t.getTaskId());
            if (!optionalUser.isEmpty()) {
                t.setUserId(optionalUser.get(0).get().getUserId());
            }
            return t;
        }).collect(Collectors.toSet());
    }
}
