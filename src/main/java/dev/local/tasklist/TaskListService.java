package dev.local.tasklist;


/**
 * Created by wangpeng on 2017/4/18.
 */
public interface TaskListService {
    TaskList add(TaskList list, String projectId);
    TaskList delete(String id);
    TaskList findById(String id);
    TaskList update(TaskList list);
}