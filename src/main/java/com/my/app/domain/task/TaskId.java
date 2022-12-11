package com.my.app.domain.task;

import com.my.app.core.domain.Entity;
import com.my.app.core.domain.EntityId;

import java.io.Serializable;

public class TaskId extends EntityId.IdAsInt {

    public static TaskId empty = new TaskId(emptyValue);

    public TaskId(Object src) {
        super(src);
    }

    public static TaskId of(Object src) {
        return new TaskId(src);
    }
}
