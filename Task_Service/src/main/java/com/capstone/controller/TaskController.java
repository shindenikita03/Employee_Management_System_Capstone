package com.capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.dto.TaskDto;
import com.capstone.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name=" CRUD REST APIs for Task resourse",
description=" CRUD REST APIs- Create Task,update Task,get Task,get all Task,delete Task")
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "CREATE task REST APIs",
    		description="create task REST APIs used to save task in a database")
    
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @Operation(summary = "GET ALL task REST APIs",
    		description="get all task REST APIs used to get all tasks from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
//        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
//    }

    @Operation(summary = "GET task by id REST API",
    		description="get task REST APIs used to get a specific task from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable("id") Long id) {
        return taskService.getTaskById(id);
    }
    
    @Operation(summary = "UPDATE task  REST API",
    		description="UPDATE task REST API is  used to update a specific task in a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(id, taskDto), HttpStatus.OK);
    }

    @Operation(summary = "DELETE task REST API",
    		description="DELETE task REST API is used to DELETE a specific task from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }
}
