package com.capstone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.capstone.dto.TaskDto;
import com.capstone.entity.Task;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.TaskRepo;
import com.capstone.service.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    void setUp() {
        task = new Task(1L, "Test Task", "Description", "Pending");
        taskDto = new TaskDto(1L, "Test Task", "Description", "Completed");
    }

    @Test
    @DisplayName("junit testing for createTask")
    void testCreateTask() {
        when(modelMapper.map(any(TaskDto.class), eq(Task.class))).thenReturn(task);
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        TaskDto result = taskService.createTask(taskDto);

        assertThat(result.getTitle()).isEqualTo("Test Task");
        verify(taskRepo, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("junit test for get all task")
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepo.findAll()).thenReturn(tasks);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        List<TaskDto> result = taskService.getAllTasks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Task");
        verify(taskRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("junit tetsing for get task by id")
    void testGetTaskById() {
        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        TaskDto result = taskService.getTaskById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(taskRepo, times(1)).findById(1L);
    }

    @Test
    @DisplayName("junit testing for task not found by id")
    void testGetTaskById_NotFound() {
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFound.class, () -> taskService.getTaskById(1L));
        verify(taskRepo, times(1)).findById(1L);
    }

    @Test
    @DisplayName("junit testing for update task")
    void testUpdateTask() {
        when(taskRepo.existsById(1L)).thenReturn(true);
        when(taskRepo.save(any(Task.class))).thenReturn(task);
        when(modelMapper.map(any(TaskDto.class), eq(Task.class))).thenReturn(task);
        when(modelMapper.map(any(Task.class), eq(TaskDto.class))).thenReturn(taskDto);

        TaskDto result = taskService.updateTask(1L, taskDto);

        assertThat(result.getTitle()).isEqualTo("Test Task");
        verify(taskRepo, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("junit testing for updated task not found")
    void testUpdateTask_NotFound() {
        when(taskRepo.existsById(1L)).thenReturn(false);

        assertThrows(IdNotFound.class, () -> taskService.updateTask(1L, taskDto));
    }

    @Test
    @DisplayName("junit testing for delete task operation")
    void testDeleteTask() {
        when(taskRepo.existsById(1L)).thenReturn(true);

        String result = taskService.deleteTask(1L);

        assertThat(result).isEqualTo("Task successfully deleted with ID 1");
        verify(taskRepo, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("junit test for task not found by id while delete operation")
    void testDeleteTask_NotFound() {
        when(taskRepo.existsById(1L)).thenReturn(false);

        assertThrows(IdNotFound.class, () -> taskService.deleteTask(1L));
    }
}
