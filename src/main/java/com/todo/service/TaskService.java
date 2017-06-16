package com.todo.service;

import com.todo.models.Task;
import com.todo.dao.jpa.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Service
public class TaskService {
	
	//To do
    @Autowired
    private TaskRepository taskRepository;
    
    public TaskService() {
    }
    

    public Task createtask(Task task) {
    	System.out.println("before save");
        return taskRepository.save(task);
    }

    public Task getTask(long id) {
        return taskRepository.findOne(id);
    }

    public void updateTask(Task task) {
    	taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    public Iterable<Task> getAllTasks() {
    	Iterable pageOfTask = taskRepository.findAll();
        return pageOfTask;
    }

    public Iterable<Task> getTasksforDate(LocalDate date) {
    	Iterable<Task> pageOfTask = taskRepository.findTaskByDate(date);
        return pageOfTask;
    }
    
}
