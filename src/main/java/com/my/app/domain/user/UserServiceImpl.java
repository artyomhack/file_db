package com.my.app.domain.user;

import com.my.app.data.task.FileTaskRepository;
import com.my.app.data.task_user.FileTaskUserRelRepository;
import com.my.app.data.user.FileUserRepository;
import com.my.app.domain.task.TaskId;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final FileUserRepository userRepository;
    private final FileTaskUserRelRepository relRepository;
    private final FileTaskRepository taskRepository;

    public UserServiceImpl(FileUserRepository userRepository, FileTaskUserRelRepository taskUserRelRepository, FileTaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.relRepository = taskUserRelRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public UserId add(String firstName, String lastName) {
       return null;
    }

    @Override
    public UserId update(UserId userId, String firstName, String lastName) {
        return null;
    }

    @Override
    public boolean deleteById(UserId userId) {
        return false;
    }

    @Override
    public void addTask(UserId userId, TaskId taskId) {

    }

    @Override
    public User findById(UserId userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<UserId> findUserIdByTaskId(TaskId taskId) {
        return relRepository.findRelations(taskId);
    }

}
