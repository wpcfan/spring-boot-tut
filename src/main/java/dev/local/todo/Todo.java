package dev.local.todo;

import dev.local.user.User;
import org.springframework.data.annotation.Id;

/**
 * Todo是一个领域对象（domain object）
 * Created by wangpeng on 2017/1/24.
 */
public class Todo {
    @Id private String id;
    private String desc;
    private boolean completed;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
