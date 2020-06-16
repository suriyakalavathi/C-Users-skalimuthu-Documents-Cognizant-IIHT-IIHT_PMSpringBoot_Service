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

import home.cognizant.pm.api.mapper.ProjectMapper;
import home.cognizant.pm.api.model.ProjectRequest;
import home.cognizant.pm.api.model.ProjectResponse;
import home.cognizant.pm.api.model.UserResponse;
import home.cognizant.pm.service.api.ProjectService;
import home.cognizant.pm.service.entity.Project;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@Validated
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @PostMapping("/projects/{managerId}")
    public ResponseEntity<UserResponse> add(@PathVariable long managerId, @Valid @RequestBody ProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
    //    project.setManagerId(managerId);
        return new ResponseEntity(projectMapper.toProjectResponse(projectService.add(project)), HttpStatus.CREATED);
    }

    @PutMapping("/projects/{managerId}")
    public ResponseEntity<ProjectResponse> edit(@PathVariable long managerId, @Valid @RequestBody ProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
        project.setManagerId(managerId);
        return new ResponseEntity(projectMapper.toProjectResponse(projectService.edit(project)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable long projectId) {
        projectService.delete(projectId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> get(@PathVariable long projectId) {
            return new ResponseEntity(projectMapper.toProjectResponse(projectService.get(projectId)), HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> getProjects() {
       return new ResponseEntity(projectMapper.toProjectResponse(projectService.getAll()), HttpStatus.OK);
    }
}
