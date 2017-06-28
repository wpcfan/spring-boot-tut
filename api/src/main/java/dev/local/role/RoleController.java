package dev.local.role;

import dev.local.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepository repository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getRoles(@RequestParam String name) {
        return StringUtils.isEmpty(name) ? repository.findAll() : repository.findByNameLike(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public Role add(@RequestBody Role role) {
        return repository.insert(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role getRoleByName(@PathVariable String id) {
        return repository.findOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Role updateRole(@PathVariable String id, @RequestBody Role updatedRole) {
        updatedRole.setId(id);
        return repository.save(updatedRole);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Role deleteRole(@PathVariable String id, @RequestBody Role deletedRole) {
        repository.delete(deletedRole);
        return deletedRole;
    }
}
