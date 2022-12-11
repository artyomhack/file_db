package com.my.app.domain.user;

import com.my.app.core.domain.DomainEntity;
import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Iterator;

@Data
@AllArgsConstructor
public class User implements DomainEntity<UserId> {

    /**
     * UserDetails
     */

    private final UserId userId;

    private String firstName;

    private String lastName;

    private Iterator<Task> tasks;

    public User(UserId userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User userOf(String firstName, String lastName) {
        return new User(
                UserId.empty,
                firstName,
                lastName
        );
    }
}
