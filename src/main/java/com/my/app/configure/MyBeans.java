package com.my.app.configure;

import com.my.app.core.data.FileEntityRepository;
import com.my.app.core.data.FileRelationRepository;
import com.my.app.data.task.FileTaskRepository;
import com.my.app.data.task_user.FileTaskUserRelRepository;
import com.my.app.data.user.FileUserRepository;
import com.my.app.domain.task.TaskService;
import com.my.app.domain.task.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.my.app")
public class MyBeans {

    @Bean
    public TaskService taskService(FileTaskRepository taskRepository, FileUserRepository userRepository,
    FileTaskUserRelRepository taskUserRealRepository) {
        return new TaskServiceImpl(taskRepository, userRepository, taskUserRealRepository);
    }

    @Bean
    public FileTaskRepository fileTaskRepository() {
        return new FileTaskRepository();
    }

    @Bean
    public FileUserRepository fileUserRepository() {
        return new FileUserRepository();
    }

    @Bean
    public FileTaskUserRelRepository fileRelationRepository() {
        return new FileTaskUserRelRepository();
    }
}
