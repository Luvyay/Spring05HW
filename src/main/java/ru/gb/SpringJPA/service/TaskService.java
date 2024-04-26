package ru.gb.SpringJPA.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringJPA.model.Task;
import ru.gb.SpringJPA.model.TaskStatus;
import ru.gb.SpringJPA.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
