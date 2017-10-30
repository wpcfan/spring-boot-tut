package dev.local.taskmgr.mongo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import dev.local.taskmgr.mongo.controllers.wrappers.ProjectMemberIds;
import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.domain.Project;
import dev.local.taskmgr.mongo.dto.CreateProjectDTO;
import dev.local.taskmgr.mongo.dto.QueryProjectDTO;
import dev.local.taskmgr.mongo.services.ProfileService;
import dev.local.taskmgr.mongo.services.ProjectService;
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
    private final ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<QueryProjectDTO> findRelated(
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
    public Project findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/projects")
    List<Profile> getUserByProject(@PathVariable String id) {
        return profileService.profilesByProject(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Project update(@PathVariable String id, @RequestBody Project project){
        project.setId(id);
        return service.update(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Project inviteMembers(@PathVariable String id, @RequestBody ProjectMemberIds memberIds) {
        return service.inviteMembers(id, memberIds.getMemberIds());
    }

}
