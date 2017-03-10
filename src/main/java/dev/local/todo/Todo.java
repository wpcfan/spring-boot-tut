package dev.local.todo;

import dev.local.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Todo是一个领域对象（domain object）
 * Created by wangpeng on 2017/1/24.
 */
@Data
public class Todo {
    @Id private String id;
    private String desc;
    private boolean completed;
    private User user;
}
