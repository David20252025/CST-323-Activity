package com.gcu.activity.repository;

import com.gcu.activity.model.Task;
import com.gcu.activity.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByDueDateAsc();

    List<Task> findByStatusOrderByDueDateAsc(TaskStatus status);
}