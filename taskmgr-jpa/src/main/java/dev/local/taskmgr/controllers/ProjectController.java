package dev.local.taskmgr.controllers;

import dev.local.taskmgr.controllers.wrappers.ProjectMemberIds;
import dev.local.taskmgr.domain.Profile;
import dev.local.taskmgr.domain.Project;
import dev.local.taskmgr.dto.CreateProjectDTO;
import dev.local.taskmgr.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangpeng on 2017/4/18.
 */
@RestController
@RequestMapping("/projects")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService service;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Project> findRelated(
            @RequestParam(value = "enabled", defaultValue = "true", required = false) boolean enabled,
            @RequestParam(value = "archived", defaultValue = "false", required = false) boolean archived,
            Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.findRelated(username, pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project add(@RequestBody CreateProjectDTO projectDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Project project = projectDTO.buildProject();
        return service.add(project, username);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/projects")
    List<Profile> getUserByProject(@PathVariable Long id) {
        return service.findById(id).getMembers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Project update(@PathVariable Long id, @RequestBody Project project){
        return service.update(project.withId(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Project inviteMembers(@PathVariable Long id, @RequestBody ProjectMemberIds memberIds) {
        return service.inviteMembers(id, memberIds.getMemberIds());
    }

}
