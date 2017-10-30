package dev.local.taskmgr.mongo.repositories;

import dev.local.taskmgr.mongo.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    @Query("{'memberIds': ?#{[0]}, 'enabled': ?#{[1]}, 'archived': ?#{[2]}}")
    Page<Project> findRelated(String userId, boolean enabled, boolean archived, Pageable pageable);
//    Page<Project> findByMembersContainingAndEnabledAndArchived(User user, boolean enabled, boolean archived, Pageable pageable);
}
