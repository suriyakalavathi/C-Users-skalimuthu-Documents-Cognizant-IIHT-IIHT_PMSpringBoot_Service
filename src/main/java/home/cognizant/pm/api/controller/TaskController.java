package home.cognizant.pm.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import home.cognizant.pm.api.mapper.TaskMapper;
import home.cognizant.pm.api.model.TaskRequest;
import home.cognizant.pm.api.model.TaskResponse;
import home.cognizant.pm.service.api.TaskService;
import home.cognizant.pm.service.entity.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@Validated
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskMapper taskMapper;

    @PostMapping("tasks")
    public ResponseEntity<TaskResponse> add(@Valid @RequestBody TaskRequest taskRequest) {   
    	 Task user = taskMapper.toTask(taskRequest);
        return new ResponseEntity<TaskResponse>(taskMapper.toTaskResponse(taskService.add(user)), HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> edit(@Valid @PathVariable long taskId, @Valid @RequestBody TaskRequest taskRequest) {
           return new ResponseEntity<TaskResponse>(taskMapper.toTaskResponse(taskService.edit(taskMapper.toTask(taskRequest))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long taskId) {      
        taskService.delete(taskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tasks/{projectId}/{taskId}")
    public ResponseEntity<TaskResponse> get(@Valid @PathVariable long projectId, @Valid @PathVariable long taskId) {     
        return new ResponseEntity<TaskResponse>(taskMapper.toTaskResponse(taskService.get(projectId, taskId)), HttpStatus.OK);
    }

    @GetMapping("/tasks/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasks(@Valid @PathVariable long projectId) {
       return new ResponseEntity(taskMapper.toTaskResponse(taskService.getAll(projectId)), HttpStatus.OK);
    }
}
