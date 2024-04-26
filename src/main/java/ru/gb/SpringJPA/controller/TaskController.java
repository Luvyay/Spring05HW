package ru.gb.SpringJPA.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.SpringJPA.model.Task;
import ru.gb.SpringJPA.model.TaskStatus;
import ru.gb.SpringJPA.service.TaskService;
import ru.gb.SpringJPA.service.TaskStatusService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    private TaskStatusService taskStatusService;

    /**
     * Метод для отображения в браузере списка со всеми задачами
     * @param model
     * @return
     */
    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        String title = "Все задачи";

        model.addAttribute("tasks", tasks);
        model.addAttribute("title", title);

        return "tasks-list";
    }

    /**
     * Метод для отображения списка задач с конкретным статусом задачи
     * @param status
     * @param model
     * @return
     */
    @GetMapping("/tasks/{status}")
    public String getAllTasks(@PathVariable(name = "status") String status, Model model) {
        List<Task> tasks = new ArrayList();

        if (taskStatusService.checkStatus(status)) {
            tasks = taskService.getTasksByStatus(taskStatusService.getStatusFromString(status));
            String title = "Все задачи со статусом: " + status;

            model.addAttribute("tasks", tasks);
            model.addAttribute("title", title);
        }

        return "tasks-list";
    }

    /**
     * Метод для выведения в браузере страницы с формой для добавления новой задачи
     * @param task
     * @param model
     * @return
     */
    @GetMapping("/task-add")
    public String saveTaskForm(Task task, Model model) {
        task.setDate(LocalDate.now());
        model.addAttribute(task);

        return "task-add";
    }

    /**
     * Метод, который принимает данные из формы и записывает эти данные в БД (добавление задачи)
     * @param task
     * @return
     */
    @PostMapping("/task-add")
    public String saveTask(Task task) {
        task.setStatus(TaskStatus.NOT_STARTED);
        taskService.addTask(task);

        return "redirect:/tasks";
    }

    /**
     * Метод для удаления конкретной задачи (по id)
     * @param id
     * @return
     */
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteTaskById(id);

        return "redirect:/tasks";
    }

    /**
     * Метод для выведения в браузере страницы с формой для обновления задачи
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/task-update/{id}")
    public String updateTaskForm(@PathVariable(name = "id") Long id, Model model) {
        Task task = taskService.findById(id);

        model.addAttribute("task", task);

        return "task-update";
    }

    /**
     * Метод, который принимает данные из формы и записывает эти данные в БД (обновление задачи без изменения статуса)
     * @param task
     * @return
     */
    @PostMapping("/task-update")
    public String updateTask(Task task) {
        TaskStatus taskStatus = taskService.findById(task.getId()).getStatus();

        task.setStatus(taskStatus);

        taskService.updateTask(task);

        return "redirect:/tasks";
    }

    /**
     * Метод, который обновляет статус задачи по id с указанием конкретного статуса
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/task-update-status/{id}/{status}")
    public String updateTaskStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "status") String status) {
        Task task = taskService.findById(id);

        if (!task.getStatus().getStatusDescription().equals(status)) {
            task.setStatus(taskStatusService.getStatusFromString(status));

            taskService.updateTask(task);
        }

        return "redirect:/tasks/" + status;
    }
}
