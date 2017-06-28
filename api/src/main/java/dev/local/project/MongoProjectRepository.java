package dev.local.project;

import dev.local.domain.Project;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Repository
public interface MongoProjectRepository extends MongoRepository<Project, String> {
    @Query("{'owner.$id': ?#{[0]}, 'enabled': ?#{[1]}, 'archived': ?#{[2]}}")
    Page<Project> findRelated(ObjectId userId, boolean enabled, boolean archived, Pageable pageable);
//    Page<Project> findByMembersContainingAndEnabledAndArchived(User user, boolean enabled, boolean archived, Pageable pageable);
}
