package dev.local.todo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangpeng on 2017/1/26.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByUserId(ObjectId userId);
}
