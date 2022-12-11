package com.my.app.core.domain;

import com.my.app.core.domain.type.Value;
import com.my.app.domain.task.TaskId;

import java.io.Serializable;
import java.util.Objects;

public interface Entity<ID extends EntityId<?>> extends ValueObject, Comparable<ID>{
    ID getId();

    Entity<ID> setIdForEntity(ID id);
}
