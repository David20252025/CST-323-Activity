package com.gcu.activity.service;

import com.gcu.activity.model.Task;
import com.gcu.activity.model.TaskStatus;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    List<Task> findByStatus(TaskStatus status);

    Task findById(Long id);

    Task save(Task task);

    void deleteById(Long id);

    long count();
}