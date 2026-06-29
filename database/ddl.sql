CREATE DATABASE IF NOT EXISTS activity_task_manager;

USE activity_task_manager;

DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(25) NOT NULL DEFAULT 'NOT_STARTED',
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM',
    due_date DATE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

INSERT INTO tasks (title, description, status, priority, due_date)
VALUES
('Create Azure student account',
 'Verify Azure for Students subscription and take screenshot.',
 'IN_PROGRESS',
 'HIGH',
 '2026-06-25'),

('Build CRUD pages',
 'Create list, details, create, edit, and delete task functionality.',
 'NOT_STARTED',
 'HIGH',
 '2026-06-27'),

('Create screencast',
 'Record the application running and demonstrate CRUD functionality.',
 'NOT_STARTED',
 'MEDIUM',
 '2026-06-28');