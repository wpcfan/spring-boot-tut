package dev.local.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wangpeng on 2017/1/31.
 */
public interface UserRepository extends MongoRepository<User, String> {
}
