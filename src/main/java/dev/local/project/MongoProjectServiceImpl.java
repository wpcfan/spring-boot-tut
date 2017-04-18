package dev.local.project;

import dev.local.taskgroup.TaskGroup;
import dev.local.taskgroup.TaskGroupRepository;
import dev.local.user.User;
import dev.local.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import static java.util.Arrays.asList;
/**
 * Created by wangpeng on 2017/4/18.
 */
@Service
public class MongoProjectServiceImpl implements ProjectService {
    private ProjectRepository repository;
    private UserRepository userRepository;
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    public MongoProjectServiceImpl(
            ProjectRepository repository,
            UserRepository userRepository,
            TaskGroupRepository taskGroupService){
        this.repository = repository;
        this.userRepository = userRepository;
        this.taskGroupRepository = taskGroupService;
    }

    @Override
    public Project add(Project project, String userId) {
        final User user = userRepository.findOne(userId);
        project.setOwner(user);
        project.setMembers(asList(user));
        project.setEnabled(true);
        project.setArchived(false);
        TaskGroup plan = new TaskGroup();
        plan.setName("计划");
        plan.setOrder(0);
        TaskGroup inProgress = new TaskGroup();
        inProgress.setName("进行中");
        inProgress.setOrder(1);
        TaskGroup done = new TaskGroup();
        done.setName("已完成");
        done.setOrder(2);
        project.setGroups(asList(
                taskGroupRepository.insert(plan),
                taskGroupRepository.insert(inProgress),
                taskGroupRepository.insert(done)));
        return repository.insert(project);
    }

    @Override
    public Project delete(String id) {
        final Project project = repository.findOne(id);
        repository.delete(id);
        return project;
    }

    @Override
    public Set<Project> findRelated(String userId) {
        final User user =  userRepository.findOne(userId);
        return repository.findByMembersContaining(user);
    }

    @Override
    public Project findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Project update(Project project) {
        repository.save(project);
        return project;
    }
}
