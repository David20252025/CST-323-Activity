package com.gcu.activity.controller;

import com.gcu.activity.model.Priority;
import com.gcu.activity.model.Task;
import com.gcu.activity.model.TaskStatus;
import com.gcu.activity.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(@RequestParam(required = false) TaskStatus status, Model model) {
        if (status == null) {
            model.addAttribute("tasks", taskService.findAll());
        } else {
            model.addAttribute("tasks", taskService.findByStatus(status));
        }

        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("selectedStatus", status);

        return "tasks/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        addFormLists(model);
        return "tasks/form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addFormLists(model);
            return "tasks/form";
        }

        Task savedTask = taskService.save(task);
        logger.info("Created task with id {}", savedTask.getId());

        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully.");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "tasks/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        addFormLists(model);
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id,
                             @Valid @ModelAttribute("task") Task task,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            task.setId(id);
            addFormLists(model);
            return "tasks/form";
        }

        Task existingTask = taskService.findById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());

        taskService.save(existingTask);
        logger.info("Updated task with id {}", id);

        redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully.");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteById(id);
        logger.warn("Deleted task with id {}", id);

        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully.");
        return "redirect:/tasks";
    }

    private void addFormLists(Model model) {
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("priorities", Priority.values());
    }
}