package com.my.app.data.task_user;

import com.my.app.core.data.FileRelationRepository;
import com.my.app.core.domain.Entity;
import com.my.app.data.task.TaskEntity;
import com.my.app.data.user.UserEntity;
import com.my.app.domain.task.TaskId;
import com.my.app.domain.user.UserId;

public class FileTaskUserRelRepository extends FileRelationRepository<TaskId, UserId> {

    public FileTaskUserRelRepository() {
        super(TaskEntity.class, UserEntity.class);
    }

    @Override
    protected UserId mapRelateIdFromString(String relateId) {
        return UserId.of(relateId);
    }

    @Override
    protected TaskId mapMainIdFromString(String mainId) {
        return TaskId.of(mainId);
    }


}
