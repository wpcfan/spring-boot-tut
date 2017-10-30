package dev.local.taskmgr.mongo.repositories;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import dev.local.taskmgr.mongo.domain.Project;
import dev.local.taskmgr.mongo.dto.QueryProjectDTO;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectRepoCustomImpl implements ProjectRepoCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<QueryProjectDTO> getJoinedProjectsWithUsers(String username, Pageable pageable) {
        List<QueryProjectDTO> projects = mongoTemplate.aggregate(newAggregation(
                unwind("memberIds"),
                match(Criteria.where("memberIds").is(username).and("enabled").is(true).and("archived").is(false)),
                lookup("profile", "memberIds", "username", "members"),
                skip(new Long(pageable.getOffset())),
                limit(pageable.getPageSize())
        ), "project", QueryProjectDTO.class)
                .getMappedResults();
        return new PageImpl<>(projects, pageable, getCount("memberIds", username));
    }

    private long getCount(String propertyName, String propertyValue) {
        Query countQuery = new Query(Criteria.where(propertyName).is(propertyValue));
        return mongoTemplate.count(countQuery, Project.class);
    }
}
