package com.my.app.domain.task;

import com.my.app.core.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Task implements DomainEntity<TaskId> {

    /**
     * TaskDetails
     */

    private final TaskId taskId;

    private String title;

    private String label;

    public static Task taskOf(String title, String label) {
        return new Task(TaskId.empty, title, label);
    }
}
