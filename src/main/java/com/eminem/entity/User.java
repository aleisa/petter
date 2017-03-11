package com.eminem.entity;
import org.springframework.data.annotation.Id;
/**
 * Created by ysen6 on 2017/2/8.
 */
public class User {
    @Id
    private String id;

    private String username;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
