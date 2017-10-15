package dev.local.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.local.domain.Project;
import dev.local.domain.Task;
import dev.local.domain.TaskList;
import lombok.Data;

import java.util.List;

@Data
public class SelectProjectDTO {
    private Project project;
    private List<TaskList> taskLists;
    private List<Task> tasks;
}
