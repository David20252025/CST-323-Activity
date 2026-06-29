package com.gcu.activity.service;

import com.gcu.activity.model.Task;
import com.gcu.activity.model.TaskStatus;
import com.gcu.activity.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        logger.info("Retrieving all tasks");
        return taskRepository.findAllByOrderByDueDateAsc();
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        logger.info("Retrieving tasks with status {}", status);
        return taskRepository.findByStatusOrderByDueDateAsc(status);
    }

    @Override
    public Task findById(Long id) {
        logger.info("Retrieving task with id {}", id);

        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id " + id));
    }

    @Override
    public Task save(Task task) {
        logger.info("Saving task with title {}", task.getTitle());
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        logger.warn("Deleting task with id {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public long count() {
        return taskRepository.count();
    }
}