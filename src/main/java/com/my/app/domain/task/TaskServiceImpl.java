package com.my.app.domain.task;

import com.my.app.data.task.FileTaskRepository;
import com.my.app.data.task.TaskEntity;
import com.my.app.data.task_user.FileTaskUserRelRepository;
import com.my.app.data.user.FileUserRepository;
import com.my.app.domain.user.UserId;

import java.util.List;

public class TaskServiceImpl implements TaskService{

    private final FileTaskRepository taskRepository;
    private final FileUserRepository userRepository;
    private final FileTaskUserRelRepository relRepository;

    public TaskServiceImpl(FileTaskRepository taskRepository, FileUserRepository userRepository, FileTaskUserRelRepository relRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.relRepository = relRepository;
    }

    @Override
    public TaskId add(String title, String label) {
        var task = Task.taskOf(title, label);

        return taskRepository.save(TaskEntity.of(task));
    }

    @Override
    public TaskId update(TaskId taskId, String title, String label) {
        var task = new Task(taskId, title, label);

        return taskRepository.save(TaskEntity.of(task));
    }

    @Override
    public boolean deleteById(TaskId taskId) {
        return taskRepository.deleteById(taskId);
    }

    @Override
    public void addUser(TaskId taskId, UserId userId) {
        relRepository.addRelations(taskId, List.of(userId));
    }

    @Override
    public Task findById(TaskId taskId) {
        var task = taskRepository.findById(taskId);
        return new Task(task.getId(), task.getTitle(), task.getLabel());
    }

    @Override
    public List<TaskListItem> findAll() {
        return taskRepository.findAll().stream().map(it -> {
            var title = it.getTitle();
            var label = it.getLabel();
            return new TaskListItem(title, label);
        }).toList();
    }

    @Override
    public List<TaskId> findTaskIdByUserId(UserId userId) {
        return relRepository.findReverseRelations(userId);
    }
}
