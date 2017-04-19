package dev.local.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Repository
public interface MongoProjectRepository extends MongoRepository<Project, String> {
}
