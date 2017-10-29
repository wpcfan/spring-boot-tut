package dev.local.taskmgr.controllers;

import dev.local.taskmgr.domain.Profile;
import dev.local.taskmgr.domain.Task;
import dev.local.taskmgr.dto.ProfileDTO;
import dev.local.taskmgr.dto.UserDTO;
import dev.local.taskmgr.feign.AuthClient;
import dev.local.taskmgr.services.ProfileService;
import dev.local.taskmgr.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final ProfileService profileService;
    private final TaskService taskService;
    private final AuthClient authClient;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    Page<Profile> search(@RequestParam(value = "filter") String filter, Pageable pageable) {
        return profileService.search(filter, pageable);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/me/tasks")
    public List<Task> findRelated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskService.findTasksByUser(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/profiles")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        UserDTO userDTO = authClient.register(UserDTO.builder()
                .username(profileDTO.getUsername())
                .password(profileDTO.getPassword())
                .build());
        if(userDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Profile profile = profileService.add(Profile.fromProfileDTO(profileDTO), profileDTO.getUsername());
        if(profile == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.badRequest().body(profile.toProfileDTO());
    }
}
