package com.keploy.demo.controllers;

import com.keploy.demo.service.TaskService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TaskControllerTestConfig {

    @Bean
    public TaskService taskService() {
        return Mockito.mock(TaskService.class);
    }
}
