package com.keploy.demo.services;

import com.keploy.demo.model.Task;
import com.keploy.demo.repository.TaskRepository;
import com.keploy.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskServiceIntegrationTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    void cleanDatabase() {
        taskRepository.deleteAll();
    }

    @Test
    void shouldCreateAndFetchTasks() {
        Task task = new Task(null, "Integration Task", "Testing DB save + fetch", false, LocalDate.of(2025, 7, 1));
        taskService.createTask(task);

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
        assertEquals("Integration Task", tasks.get(0).getTitle());
    }

    @Test
    void shouldUpdateTaskInDatabase() {
        Task original = new Task(null, "Old", "Old desc", false, LocalDate.now());
        Task saved = taskService.createTask(original);

        Task update = new Task(saved.getId(), "Updated", "New desc", true, LocalDate.now().plusDays(5));
        Task updated = taskService.updateTask(saved.getId(), update);

        assertEquals("Updated", updated.getTitle());
        assertTrue(updated.isCompleted());
    }

    @Test
    void shouldDeleteTaskFromDatabase() {
        Task task = new Task(null, "To Delete", "Temp", false, LocalDate.now());
        Task saved = taskService.createTask(task);

        taskService.deleteTask(saved.getId());

        assertEquals(0, taskRepository.findAll().size());
    }
}


