package dev.local.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangpeng on 2017/1/31.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
