package com.my.app.data.user;

import com.my.app.core.domain.Entity;
import com.my.app.data.roles.Role;
import com.my.app.data.task.TaskEntity;
import com.my.app.domain.task.Task;
import com.my.app.domain.user.User;
import com.my.app.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;

@Data
@AllArgsConstructor
public class UserEntity implements Entity<UserId> {

    private final UserId userId;
    private String firstName;
    private String lastName;
    private Role role;
    private final Iterable<TaskEntity> tasks;

    public static UserEntity of(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                new ArrayList<>()
        );
    }

    @Override
    public UserId getId() {
        return userId;
    }

    @Override
    public UserEntity setIdForEntity(UserId id) {
        return new UserEntity(
                id,
                getFirstName(),
                getLastName(),
                getRole(),
                getTasks()
        );
    }

    @Override
    public int compareTo(UserId o) {
        return this.compareTo(o);
    }

    public UserId getUserId() {
        return userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }
}
