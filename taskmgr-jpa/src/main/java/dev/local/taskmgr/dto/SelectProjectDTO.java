package dev.local.taskmgr.dto;

import lombok.Data;
import dev.local.taskmgr.domain.Project;
import dev.local.taskmgr.domain.Task;
import dev.local.taskmgr.domain.TaskList;
import java.util.List;

@Data
public class SelectProjectDTO {
    private Project project;
    private List<TaskList> taskLists;
    private List<Task> tasks;
}
