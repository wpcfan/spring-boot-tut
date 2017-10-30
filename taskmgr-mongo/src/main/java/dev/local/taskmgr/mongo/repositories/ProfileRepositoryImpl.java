package dev.local.taskmgr.mongo.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import dev.local.taskmgr.mongo.domain.Profile;
import dev.local.taskmgr.mongo.dto.QueryProjectDTO;

@Repository
public class ProfileRepositoryImpl implements ProfileRepoCustom{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProfileRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Profile> findUsersInProject(String projectId) {
        List<QueryProjectDTO> projects = mongoTemplate.aggregate(newAggregation(
                unwind("memberIds"),
                match(Criteria.where("_id").is(new ObjectId(projectId))),
                lookup("profile", "memberIds", "username", "members")
        ), "project", QueryProjectDTO.class)
                .getMappedResults();

        return projects.get(0).getMembers();
    }
}
