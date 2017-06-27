package dev.local.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("hasRole('USER')")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> findRelated(@RequestHeader(value = "userId") String userId) {
        return service.findRelated(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    Task add(@RequestBody Task addedTask) {
        return service.add(addedTask);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Task update(@PathVariable String id, @RequestBody Task updatedTask) {
        updatedTask.setId(id);
        return service.update(updatedTask);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Task remove(@PathVariable String id) {
        return service.delete(id);
    }
}
