package com.capstone.controller;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.dto.AllApiResponnseDto;
import com.capstone.dto.ApiResponseDto;
import com.capstone.dto.EmployeeDto;
import com.capstone.dto.PerformanceApiResponseDto;
import com.capstone.dto.TaskApiResponseDto;
import com.capstone.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="CRUD REST APIs for employee resourse",
description="CRUD REST APIs- Create employee,update employee,delete employee,get employee,getall employees")
@RestController
@CrossOrigin
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService service;
    
    @Autowired
    private ModelMapper map;
    
    @Operation(summary = "CREATE employee REST APIs",
    		description="CREATE employee REST APIs used to save employee in a database")
    
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto createdEmployeeDto = service.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }
    
    @Operation(summary = "GET ALL employee REST APIs",
    		description="get all employees REST APIs used to get all employees from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeesDto = service.getAllEmployees();
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }
    
    @Operation(summary = "GET employee by id REST API",
    		description="get employee REST APIs used to get a specific employee from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = service.getEmployeeById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);

    }
    
    @Operation(summary = "UPDATE employee  REST API",
    		description="UPDATE employee REST API is  used to update a specific employee in a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = service.updateEmployee(id, employeeDto);
        return updatedEmployeeDto != null ? ResponseEntity.ok(updatedEmployeeDto): ResponseEntity.notFound().build(); 
  	
    }
    
    @Operation(summary = "DELETE employee REST API",
    		description="DELETE employee REST API is used to DELETE a specific employee from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        String response = service.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "GET Employee by code and id REST API",
    		description="get Employee REST APIs used to get a specific Employee from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/code/{id}")
    public ResponseEntity<ApiResponseDto> getMethodName(@PathVariable("id") Long id) {
    	ApiResponseDto apiResponseDto = service.getEmployeeByIdAndCode(id);
    	
    	return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    
    @Operation(summary = "GET task by id REST API",
    		description="get task REST APIs used to get a specific task from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskApiResponseDto> getEmployeeAndTask(@PathVariable("id") Long id) {
    	TaskApiResponseDto taskApiResponseDto = service.getTaskAndEmployee(id);
    	
    	return new ResponseEntity<>(taskApiResponseDto, HttpStatus.OK);
    }
    
    @Operation(summary = "GET performance by id REST API",
    		description="get performance REST APIs used to get a specific performance from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/performance/{id}")
    public ResponseEntity<PerformanceApiResponseDto> getEmployeeAndPerformance(@PathVariable("id") Long id)
    {
    	PerformanceApiResponseDto performanceApi = service.getEmployeePerformance(id);
    	
    	return new ResponseEntity<>(performanceApi , HttpStatus.OK);
    }
    
    @Operation(summary = "GET allresponse by id REST API",
    		description="get allresponse REST APIs used to get all response from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/all/{id}")
    public ResponseEntity<AllApiResponnseDto> getAllService(@PathVariable("id") Long id)
    {
    	AllApiResponnseDto allApiDto = service.getAllServices(id);
    	
    	return new ResponseEntity<>(allApiDto, HttpStatus.OK);
    }

}
