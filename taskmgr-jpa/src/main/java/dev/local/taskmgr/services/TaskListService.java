package dev.local.services;

import dev.local.domain.TaskList;

import java.util.List;

/**
 * Created by wangpeng on 2017/4/18.
 */
public interface TaskListService {
    TaskList add(TaskList list);
    TaskList delete(String id);
    TaskList findById(String id);
    TaskList update(TaskList list);
    List<TaskList> findByProjectId(String projectId);
    List<TaskList> swapOrder(String srcTaskListId, String targetTaskListId);
}
