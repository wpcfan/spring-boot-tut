package dev.local.todo;

import java.util.List;

/**
 * Created by wangpeng on 2017/3/2.
 */
public interface TodoService {
    Todo addTodo(Todo todo);
    Todo deleteTodo(String id);
    List<Todo> findAll(String userId);
    Todo findById(String id);
    Todo update(Todo todo);
}
