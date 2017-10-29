package dev.local.taskmgr.services;


import dev.local.taskmgr.domain.TaskList;

import java.util.List;

/**
 * Created by wangpeng on 2017/4/18.
 */
public interface TaskListService {
    TaskList add(TaskList list);
    TaskList delete(Long id);
    TaskList findById(Long id);
    TaskList update(TaskList list);
    List<TaskList> findByProjectId(Long projectId);
    List<TaskList> swapOrder(Long srcTaskListId, Long targetTaskListId);
}
