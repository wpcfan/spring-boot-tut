package dev.local.role;

import dev.local.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
    List<Role> findByNameLike(String name);
}
