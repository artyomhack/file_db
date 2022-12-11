package com.my.app.domain.task;

import com.my.app.domain.user.UserId;

import java.util.List;

public interface TaskService {

    TaskId add(String title, String label);

    TaskId update(TaskId taskId, String title, String label);

    boolean deleteById(TaskId taskId);

    void addUser(TaskId taskId, UserId userId);

    Task findById(TaskId taskId);

    List<TaskListItem> findAll();

    List<TaskId> findTaskIdByUserId(UserId userId);
}
