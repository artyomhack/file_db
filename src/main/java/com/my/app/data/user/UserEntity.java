package com.my.app.data.user;

import com.my.app.core.domain.Entity;
import com.my.app.data.task.TaskEntity;
import com.my.app.domain.user.User;
import com.my.app.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class UserEntity implements Entity<UserId> {

    private final UserId userId;
    private final String firstName;
    private final String lastName;
    private final Iterable<TaskEntity> tasks;

    public static UserEntity of(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
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
                getTasks()
        );
    }

    @Override
    public int compareTo(UserId o) {
        return this.compareTo(o);
    }
}
