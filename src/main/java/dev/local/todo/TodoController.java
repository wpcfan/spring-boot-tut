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
    public List<Todo> getAllTodos(@RequestHeader(value = "username") String username) {
        return service.findAll(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    Todo addTodo(@RequestBody Todo addedTodo) {
        return service.addTodo(addedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Todo getTodo(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        updatedTodo.setId(id);
        return service.update(updatedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Todo removeTodo(@PathVariable String id) {
        return service.deleteTodo(id);
    }
}
