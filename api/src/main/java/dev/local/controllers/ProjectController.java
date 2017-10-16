package dev.local.controllers;

import dev.local.domain.Project;
import dev.local.dto.CreateProjectDTO;
import dev.local.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangpeng on 2017/4/18.
 */
@RestController
@RequestMapping("/projects")
@PreAuthorize("hasRole('USER')")
public class ProjectController {

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Project> findRelated(
            @RequestParam(value = "enabled", defaultValue = "true", required = false) boolean enabled,
            @RequestParam(value = "archived", defaultValue = "false", required = false) boolean archived,
            Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.findRelated(username, enabled, archived, pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project add(@RequestBody CreateProjectDTO projectDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Project project = projectDTO.buildProject();
        return service.add(project, username);
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
