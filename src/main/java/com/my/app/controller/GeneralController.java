package com.my.app.controller;

import com.my.app.data.task_user.FileTaskUserRelRepository;
import com.my.app.domain.task.Task;
import com.my.app.domain.task.TaskId;
import com.my.app.domain.task.TaskService;
import com.my.app.domain.user.UserService;
import com.my.app.domain.user_task.UserTaskService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("/task")
public class GeneralController {

    private final TaskService taskService;
    private final UserService userService;
    private final UserTaskService relService;

    public GeneralController(TaskService taskService, UserService userService, UserTaskService relService) {
        this.taskService = taskService;
        this.userService = userService;
        this.relService = relService;
    }


    @GetMapping("/create")
    public ModelAndView showCreateTask() {
        return new ModelAndView("task_form");
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView createTask(@ModelAttribute("task") Task task) {
        var data = taskService.findById(taskService.add(task.getTitle(), task.getLabel()));

        return Objects.nonNull(data) ?
                new ModelAndView("redirect:/task/" + data.getTaskId().getValue()) :
                new ModelAndView("error");
    }

    @GetMapping("/{id:[0-9]+}")
    public ModelAndView thisTask(@PathVariable Integer id) {
        var task = taskService.findById(TaskId.of(id));
        var model = new ModelAndView();

        if (Objects.nonNull(task)) {
            model.getModelMap().addAttribute("title", task.getTitle());
            model.getModelMap().addAttribute("label", task.getLabel());

            model.setViewName("task_this");
            return model;
        }
        else return new ModelAndView("error");
    }

    @GetMapping("/list")
    public ModelAndView getListTask() {
        var data = taskService.findAll();
        var model = new ModelAndView();
        if (Objects.isNull(data)) return new ModelAndView("error");

        model.getModelMap().addAttribute("tasks", data);
        model.setViewName("task_list");

        return model;
    }
}
