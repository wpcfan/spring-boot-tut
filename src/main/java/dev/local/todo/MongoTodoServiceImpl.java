package dev.local.todo;

import dev.local.user.User;
import dev.local.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoTodoServiceImpl implements TodoService{
    private final TodoRepository repository;
    private final UserRepository userRepository;

    @Autowired
    MongoTodoServiceImpl(
            TodoRepository repository,
            UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Todo add(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Todo delete(String id) {
        Todo deletedTodo = repository.findOne(id);
        repository.delete(id);
        return deletedTodo;
    }

    @Override
    public List<Todo> findRelated(String userId) {
        final User user = userRepository.findOne(userId);
        return repository.findByParticipantsContaining(user);
    }

    @Override
    public List<Todo> findByGroupId(String groupId) {
        return repository.findByGroupId(groupId);
    }

    @Override
    public Todo findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Todo update(Todo todo) {
        repository.save(todo);
        return todo;
    }
}
