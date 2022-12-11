package com.my.app.domain.user;

import com.my.app.domain.task.TaskId;

import java.util.List;

public interface UserService {

    UserId add(String firstName, String lastName);

    UserId update(UserId userId, String firstName, String lastName);

    boolean deleteById(UserId userId);

    void addTask(UserId userId, TaskId taskId);

    User findById(UserId userId);

    List<User> findAll();

    List<UserId> findUserIdByTaskId(TaskId taskId);

}
