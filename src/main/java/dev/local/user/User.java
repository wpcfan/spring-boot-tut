package dev.local.user;

import org.springframework.data.annotation.Id;

/**
 * Created by wangpeng on 2017/1/31.
 */
public class User {
    @Id private String id;
    private String username;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
