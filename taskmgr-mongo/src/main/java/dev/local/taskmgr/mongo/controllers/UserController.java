package dev.local.taskmgr.mongo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.domain.Task;
import dev.local.taskmgr.mongo.services.ProfileService;
import dev.local.taskmgr.mongo.services.TaskService;
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
}
