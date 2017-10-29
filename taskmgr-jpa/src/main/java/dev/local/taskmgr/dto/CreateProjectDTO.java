package dev.local.taskmgr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.local.taskmgr.domain.Project;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProjectDTO {
    private String name;
    private String desc;
    private String coverImg;

    public Project buildProject() {
        return Project.builder()
                .name(name)
                .desc(desc)
                .coverImg(coverImg)
                .build();
    }
}
