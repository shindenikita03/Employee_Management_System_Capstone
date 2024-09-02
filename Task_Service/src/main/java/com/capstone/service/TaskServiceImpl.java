package com.capstone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.dto.TaskDto;
import com.capstone.entity.Task;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.TaskRepo;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapper.map(taskDto, Task.class);
        Task savedTask = repository.save(task);
        return mapper.map(savedTask, TaskDto.class);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = repository.findAll();
        return tasks.stream()
                    .map(task -> mapper.map(task, TaskDto.class))
                    .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new IdNotFound("Task with ID " + id + " not found."));
        return mapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        if (!repository.existsById(id)) {
            throw new IdNotFound("Task with ID " + id + " not found.");
        }
        Task task = mapper.map(taskDto, Task.class);
        task.setId(id);
        Task updatedTask = repository.save(task);
        return mapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public String deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new IdNotFound("Task with ID " + id + " not found.");
        }
        repository.deleteById(id);
        return "Task successfully deleted with ID " + id;
    }
}


