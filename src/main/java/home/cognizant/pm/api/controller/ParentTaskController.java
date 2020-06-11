package home.cognizant.pm.api.controller;

import home.cognizant.pm.api.mapper.ParentTaskMapper;
import home.cognizant.pm.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import home.cognizant.pm.api.model.ParentTaskRequest;
import home.cognizant.pm.api.model.ParentTaskResponse;
import home.cognizant.pm.service.api.ParestTaskService;

import javax.validation.Valid;
import java.util.List;

@Slf4j

@RestController
@Validated
public class ParentTaskController {

    @Autowired
    ParestTaskService parentTaskService;

    @Autowired
    ParentTaskMapper parentTaskMapper;

    @PostMapping("/parentTasks")
    public ResponseEntity<UserResponse> add(@Valid @RequestBody ParentTaskRequest parentTaskRequest) {
       return new ResponseEntity(parentTaskMapper.toParentTaskResponse(parentTaskService.add(parentTaskMapper.toParentTask(parentTaskRequest))), HttpStatus.CREATED);
    }

    @PutMapping("/parentTasks/{parentTaskId}")
    public ResponseEntity<ParentTaskResponse> edit(@Valid @RequestBody ParentTaskRequest parentTaskRequest) {
        return new ResponseEntity(parentTaskMapper.toParentTaskResponse(parentTaskService.add(parentTaskMapper.toParentTask(parentTaskRequest))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/parentTasks/{parentTaskId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long parentTaskId) {
        parentTaskService.delete(parentTaskId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/parentTasks/{parentTaskId}")
    public ResponseEntity<ParentTaskResponse> get(@PathVariable long parentTaskId) {
       return new ResponseEntity(parentTaskMapper.toParentTaskResponse(parentTaskService.get(parentTaskId)), HttpStatus.OK);
    }

    @GetMapping("/parentTasks")
    public ResponseEntity<List<ParentTaskResponse>> getProjects() {
       return new ResponseEntity(parentTaskMapper.toParentTaskResponse(parentTaskService.getAll()), HttpStatus.OK);
    }
}