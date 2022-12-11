package com.my.app.data.task;

import com.my.app.core.domain.Entity;
import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;
import com.my.app.domain.task.TaskListItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Getter
public class TaskEntity implements Entity<TaskId>{

    private final TaskId id;
    private final String title;
    private final String label;
    private final Iterable<TaskListItem> users;

    public static TaskEntity of (Task task) {
        return new TaskEntity(
                task.getTaskId(),
                task.getTitle(),
                task.getLabel(),
                new ArrayList<>()
        );
    }

    @Override
    public Entity<TaskId> setIdForEntity(TaskId id) {
        return new TaskEntity(
                id,
                getTitle(),
                getLabel(),
                getUsers()
        );
    }

    @Override
    public int compareTo(TaskId o) {
        return this.compareTo(o);
    }
}
