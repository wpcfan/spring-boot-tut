package dev.local.project;

import dev.local.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 项目信息存储
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{
    Set<Project> findByMembersContaining(User user);
}
