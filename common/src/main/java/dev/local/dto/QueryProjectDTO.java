package dev.local.dto;

import dev.local.domain.Profile;
import dev.local.domain.Project;
import lombok.Data;

import java.util.List;

@Data
public class QueryProjectDTO extends Project {
    private List<Profile> members;
}
