package com.keploy.demo.services;

import com.keploy.demo.model.Task;
import com.keploy.demo.repository.TaskRepository;
import com.keploy.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {
        Task task = new Task(1L, "Learn Spring Boot", "Study REST APIs with keploy", true, LocalDate.of(2025, 6, 30));
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
        assertEquals("Learn Spring Boot", tasks.get(0).getTitle());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateNewTask() {
        Task newTask = new Task(null, "Write Unit Test", "Using Mockito", false, LocalDate.of(2025, 7, 1));
        Task savedTask = new Task(1L, "Write Unit Test", "Using Mockito", false, LocalDate.of(2025, 7, 1));

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(newTask);

        assertNotNull(result.getId());
        assertEquals("Write Unit Test", result.getTitle());
        verify(taskRepository, times(1)).save(newTask);
    }

    @Test
    void shouldUpdateExistingTask() {
        Task existingTask = new Task(1L, "Old Task", "Outdated", false, LocalDate.of(2025, 6, 1));
        Task updatedInput = new Task(1L, "Updated Task", "Refreshed", true, LocalDate.of(2025, 7, 1));

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedInput);

        Task result = taskService.updateTask(1L, updatedInput);

        assertEquals("Updated Task", result.getTitle());
        assertEquals("Refreshed", result.getDescription());
        assertTrue(result.isCompleted());
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void shouldDeleteTaskById() {
        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}
