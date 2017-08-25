package dev.local.controllers;

import dev.local.domain.TaskList;
import dev.local.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 任务分组的API，由于任务分组只是一个项目中的逻辑上的分类，因此不提供列表API
 * 需要列表可以取得项目信息，在项目中会有该项目的列表信息。
 */
@RestController
@RequestMapping("/tasklists")
@PreAuthorize("hasRole('USER')")
public class TaskListController {

    private TaskListService service;

    @Autowired
    public TaskListController(TaskListService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskList add(@RequestBody TaskList list, @RequestHeader(value = "projectId") String projectId){
        return service.add(list, projectId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskList findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskList update(@PathVariable String id, @RequestBody TaskList group){
        group.setId(id);
        return service.update(group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TaskList delete(@PathVariable String id){
        return service.delete(id);
    }
}
