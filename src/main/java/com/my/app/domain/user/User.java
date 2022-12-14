package com.my.app.domain.user;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.my.app.core.domain.DomainEntity;
import com.my.app.data.roles.Role;
import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class User implements DomainEntity<UserId> {

    /**
     * UserDetails
     */

    private final UserId userId;

    private String firstName;

    private String lastName;

    private Role role;

    private List<Task> tasks;

    public static User userOf(String firstName, String lastName) {
        return new User(UserId.empty, firstName, lastName, Role.USER, new ArrayList<>()); // role?
    }
}
