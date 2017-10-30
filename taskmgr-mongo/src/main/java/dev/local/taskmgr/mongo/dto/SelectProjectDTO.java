package dev.local.taskmgr.mongo.dto;

import dev.local.taskmgr.mongo.domain.Project;
import dev.local.taskmgr.mongo.domain.Task;
import dev.local.taskmgr.mongo.domain.TaskList;
import java.util.List;
import lombok.Data;

@Data
public class SelectProjectDTO {
    private Project project;
    private List<TaskList> taskLists;
    private List<Task> tasks;
}
