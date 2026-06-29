package com.gcu.activity.controller;

import com.gcu.activity.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("taskCount", taskService.count());
        return "home";
    }
}