package dev.local.todo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Created by wangpeng on 2017/1/26.
 */
@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends MongoRepository<Todo, String>{
    @Query("{ 'user._id': ?0, 'desc': { '$regex': ?1} }")
    List<Todo> searchTodos(@Param("userId") ObjectId userId, @Param("desc") String desc);
}
