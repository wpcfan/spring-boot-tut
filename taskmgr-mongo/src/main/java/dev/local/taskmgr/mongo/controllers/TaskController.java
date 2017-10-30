package dev.local.taskmgr.mongo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import dev.local.taskmgr.mongo.domain.Task;
import dev.local.taskmgr.mongo.services.TaskService;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService service;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Task> findByTaskListId(
            @RequestParam(value = "taskListId") String taskListId,
            Pageable pageable) {
        return service.findByListId(taskListId, pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    Task add(@RequestBody Task addedTask) {
        return service.add(addedTask);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    Task update(@PathVariable String id, @RequestBody Task updatedTask) {
        updatedTask.setId(id);
        return service.update(updatedTask);
    }

    @RequestMapping(value = "/{id}/toggle", method = RequestMethod.PATCH)
    Task toggle(@PathVariable String id) {
        return service.toggle(id);
    }

    @RequestMapping(value = "/{id}/move/{listId}", method = RequestMethod.PATCH)
    Task move(@PathVariable String id, @PathVariable String listId) {
        return service.move(id, listId);
    }

    @RequestMapping(value = "/moveAll", method = RequestMethod.PATCH)
    List<Task> moveAll(@RequestParam("srcListId") String srcListId, @RequestParam("targetListId") String targetListId) {
        return service.moveAll(srcListId, targetListId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void remove(@PathVariable String id) {
        service.delete(id);
    }
}
