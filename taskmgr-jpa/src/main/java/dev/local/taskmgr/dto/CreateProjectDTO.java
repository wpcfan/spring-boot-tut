package dev.local.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.local.domain.Project;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProjectDTO {
    private String name;
    private String desc;
    private String coverImg;

    public Project buildProject() {
        Project project = new Project();
        project.setName(this.name);
        project.setDesc(this.desc);
        project.setCoverImg(this.coverImg);
        return project;
    }
}
