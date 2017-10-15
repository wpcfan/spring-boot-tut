package dev.local.controllers;

import dev.local.domain.Profile;
import dev.local.domain.Task;
import dev.local.domain.User;
import dev.local.dto.CreateUserDTO;
import dev.local.repositories.ProfileRepository;
import dev.local.repositories.TaskRepository;
import dev.local.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;
    private final ProfileRepository profileRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserController(
            UserRepository repository,
            ProfileRepository profileRepository,
            TaskRepository taskRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
        this.taskRepository = taskRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    User addUser(@RequestBody CreateUserDTO addedUser) {
        User userAdd = repository.insert(addedUser.buildUser());
        Profile profileAdd = addedUser.buildProfile();
        profileAdd.setUsername(userAdd.getUsername());
        profileRepository.insert(profileAdd);
        return userAdd;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tasks")
    public List<Task> findRelated(@PathVariable String id) {
        String username = repository.findOne(id).getUsername();
        return taskRepository.findByParticipantIdsContaining(username);
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        return repository.findOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        return repository.save(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    User removeUser(@PathVariable String id) {
        User deletedUser = repository.findOne(id);
        repository.delete(id);
        return deletedUser;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value="username") String username) {
        return repository.findByUsername(username);
    }
}
