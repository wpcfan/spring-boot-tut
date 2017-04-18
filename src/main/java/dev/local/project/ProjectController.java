package dev.local.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by wangpeng on 2017/4/18.
 */
@RestController
@RequestMapping("/projects")
@PreAuthorize("hasRole('USER')")
public class ProjectController {

    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Project> findRelated(@RequestHeader(value = "userId") String userId) {
        return service.findRelated(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project add(@RequestBody Project project, @RequestHeader(value = "userId") String userId){
        return service.add(project, userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Project update(@PathVariable String id, @RequestBody Project project){
        project.setId(id);
        return service.update(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Project delete(@PathVariable String id){
        return service.delete(id);
    }
}
