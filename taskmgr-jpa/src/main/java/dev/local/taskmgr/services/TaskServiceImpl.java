package dev.local.taskmgr.services;

import dev.local.taskmgr.domain.Profile;
import dev.local.taskmgr.domain.Task;
import dev.local.taskmgr.domain.TaskList;
import dev.local.taskmgr.repositories.ProfileRepository;
import dev.local.taskmgr.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final ProfileRepository profileRepository;

    @Override
    public Task add(final Task task) {
        return repository.save(task);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        final Profile profile = profileRepository.findByUsername(username);
        return  profile == null ?  new ArrayList<>() : profile.getTasksJoined();
    }

    @Override
    public Page<Task> findByListId(Long taskListId, Pageable pageable) {
        return repository.findByTaskListId(taskListId, pageable);
    }

    @Override
    public Task findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Task update(Task task) {
        return repository.save(task);
    }

    @Override
    public Task toggle(final Long id) {
        Task task = findById(id);
        return repository.save(task.withCompleted(!task.isCompleted()));
    }

    @Override
    public Task move(final Long id, final Long taskListId) {
        final Task task = findById(id);
        return repository.save(task.withTaskList(TaskList.builder().id(taskListId).build()));
    }

    @Override
    public List<Task> moveAll(final Long srcListId, final Long targetListId) {
        final List<Task> srcTasks = repository.findByTaskListId(srcListId);
        final Stream<Task> srcStream = srcTasks.stream()
            .map(task -> task.withTaskList(TaskList.builder().id(targetListId).build()));
        final List<Task> targetTasks = repository.findByTaskListId(targetListId);
        final Stream<Task> targetStream = targetTasks.stream()
            .map(task -> task.withTaskList(TaskList.builder().id(srcListId).build()));
        final Stream<Task> combinedStream = Stream.concat(srcStream, targetStream);
        final Collection<Task> collectionCombined =
                combinedStream.collect(Collectors.toList());
        return repository.save(collectionCombined);
    }
}
