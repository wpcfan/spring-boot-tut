package dev.local.todo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2017/1/24.
 */
@RestController
public class TodoController {
    @RequestMapping("/todos")
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        Todo item1 = new Todo();
        item1.setId("1");
        item1.setCompleted(false);
        item1.setDesc("go swimming");
        todos.add(item1);
        Todo item2 = new Todo();
        item2.setId("1");
        item2.setCompleted(true);
        item2.setDesc("go for lunch");
        todos.add(item2);
        return todos;
    }
}
