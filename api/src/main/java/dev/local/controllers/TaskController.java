package dev.local.controllers;

import dev.local.domain.Task;
import dev.local.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("hasRole('USER')")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Task remove(@PathVariable String id) {
        return service.delete(id);
    }
}
