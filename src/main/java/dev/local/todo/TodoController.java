package dev.local.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@PreAuthorize("hasRole('USER')")
public class TodoController {

    private TodoService service;

    @Autowired
    public TodoController(TodoService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> findRelated(@RequestHeader(value = "userId") String userId) {
        return service.findRelated(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    Todo add(@RequestBody Todo addedTodo) {
        return service.add(addedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Todo get(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Todo update(@PathVariable String id, @RequestBody Todo updatedTodo) {
        updatedTodo.setId(id);
        return service.update(updatedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Todo remove(@PathVariable String id) {
        return service.delete(id);
    }
}
