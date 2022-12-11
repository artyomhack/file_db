package com.my.app.data.task;

import com.my.app.core.data.FileEntityRepository;
import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;

import java.util.*;
import java.util.stream.Collectors;

public class FileTaskRepository extends FileEntityRepository<TaskEntity, TaskId> {

    public FileTaskRepository() {
        super(TaskEntity.class);
    }

    @Override
    protected TaskId mapOfSrc(Object src) {
        return TaskId.of(src);
    }

    @Override
    public TaskId getNextId() {
        //TaskId Code 24...
        List<TaskId> list;

        if (Objects.equals(findAll(), Collections.emptyList())) {
            list = new ArrayList<>();
            list.add(TaskId.of(240000));
            return list.get(0);
        }
        //???
        list = findAll().stream().map(TaskEntity::getId).collect(Collectors.toList());

        list.add(TaskId.of(list.get(list.size()-1).getValue() + 1));

        return list.get(list.size()-1);
    }
}
