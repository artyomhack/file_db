package com.my.app.core.domain;

import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;

public interface DomainEntity<ID extends EntityId<?>> extends ValueObject{

}
