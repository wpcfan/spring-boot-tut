package dev.local.taskgroup;

import dev.local.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务分组的API，由于任务分组只是一个项目中的逻辑上的分类，因此不提供列表API
 * 需要列表可以取得项目信息，在项目中会有该项目的列表信息。
 */
@RestController
@RequestMapping("/taskgroups")
@PreAuthorize("hasRole('USER')")
public class TaskGroupController {

    private TaskGroupService service;

    @Autowired
    public TaskGroupController(TaskGroupService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskGroup add(@RequestBody TaskGroup group, @RequestHeader(value = "projectId") String projectId){
        return service.add(group, projectId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TaskGroup findById(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TaskGroup update(@PathVariable String id, @RequestBody TaskGroup group){
        group.setId(id);
        return service.update(group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TaskGroup delete(@PathVariable String id){
        return service.delete(id);
    }
}
