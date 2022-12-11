package com.my.app.domain.task;

import com.my.app.core.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class TaskListItem implements DomainEntity<TaskId>{
    private String title;
    private String label;
}
