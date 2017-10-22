package dev.local.controllers;

import dev.local.domain.Profile;
import dev.local.domain.Task;
import dev.local.domain.User;
import dev.local.dto.CreateUserDTO;
import dev.local.services.ProfileService;
import dev.local.services.TaskService;
import dev.local.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class UserController {
    private final UserService service;
    private final ProfileService profileService;
    private final TaskService taskService;

    public UserController(
            UserService service,
            ProfileService profileService,
            TaskService taskService) {
        this.service = service;
        this.profileService = profileService;
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    User addUser(@RequestBody CreateUserDTO addedUser) {
        User userAdd = service.add(addedUser.buildUser());
        Profile profileAdd = addedUser.buildProfile();
        profileService.add(profileAdd, userAdd.getUsername());
        return userAdd;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<User> getUsers(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/tasks")
    public List<Task> findRelated(@PathVariable String id) {
        String username = service.findById(id).getUsername();
        return taskService.findTasksByUser(username);
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        return service.update(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    User removeUser(@PathVariable String id) {
        User deletedUser = service.findById(id);
        service.delete(id);
        return deletedUser;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value="username") String username) {
        return service.findByUsername(username);
    }
}
