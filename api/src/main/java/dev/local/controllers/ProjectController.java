package dev.local.controllers;

import dev.local.domain.Project;
import dev.local.domain.User;
import dev.local.dto.CreateProjectDTO;
import dev.local.services.ProjectService;
import dev.local.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * Created by wangpeng on 2017/4/18.
 */
@RestController
@RequestMapping("/projects")
@PreAuthorize("hasRole('USER')")
public class ProjectController {

    private ProjectService service;
    private UserService userService;

    @Autowired
    public ProjectController(
            ProjectService service,
            UserService userService){
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Project> findRelated(
            @RequestParam(value = "enabled", defaultValue = "true", required = false) boolean enabled,
            @RequestParam(value = "archived", defaultValue = "false", required = false) boolean archived,
            Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        return service.findRelated(user.getId(), enabled, archived, pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project add(@RequestBody CreateProjectDTO projectDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Project project = projectDTO.buildProject();
        return service.add(project, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Project update(@PathVariable String id, @RequestBody Project project){
        project.setId(id);
        return service.update(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Project delete(@PathVariable String id){
        return service.delete(id);
    }
}
