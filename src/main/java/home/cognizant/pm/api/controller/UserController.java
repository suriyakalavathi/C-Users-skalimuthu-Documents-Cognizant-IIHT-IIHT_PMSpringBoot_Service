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

import home.cognizant.pm.api.mapper.UserMapper;
import home.cognizant.pm.api.model.UserRequest;
import home.cognizant.pm.api.model.UserResponse;
import home.cognizant.pm.service.api.UserService;
import home.cognizant.pm.service.entity.Project;
import home.cognizant.pm.service.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> add(@Valid @RequestBody UserRequest userRequest) {
    	 User user = userMapper.toUser(userRequest);
        return new ResponseEntity(userMapper.toUserResponse(userService.add(user)), HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<List<UserResponse>> edit(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity(userMapper.toUserResponse(userService.edit(userMapper.toUser(userRequest))), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long userId) {
        userService.delete(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable long userId) {
        return new ResponseEntity(userMapper.toUserResponse(userService.get(userId)), HttpStatus.OK);
    }

    @GetMapping("/managers/{projectId}")
    public ResponseEntity<List<UserResponse>> getManagers(@PathVariable long projectId) {
           return new ResponseEntity(userMapper.toUserResponse(userService.getManagers(projectId)), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity(userMapper.toUserResponse(userService.getAllWithUniqueEmployeeId()), HttpStatus.OK);
    }
}
