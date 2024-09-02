package com.capstone.controller;

import java.util.List;

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

import com.capstone.dto.DepartmentDto;
import com.capstone.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name=" CRUD REST APIs for Department resourse",
description=" CRUD REST APIs- Create department,update department,get department,get all department,delete department")
@RestController
@CrossOrigin
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "CREATE department REST APIs",
    		description="create department REST APIs used to save department in a database")
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @Operation(summary = "GET ALL department REST APIs",
    		description="get all department REST APIs used to get all departments from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @Operation(summary = "GET department by id REST API",
    		description="get department REST APIs used to get a specific department from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @Operation(summary = "UPDATE department  REST API",
    		description="UPDATE department REST API is  used to update a specific department in a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(id, departmentDto), HttpStatus.OK);
    }

    @Operation(summary = "DELETE department REST API",
    		description="DELETE department REST API is used to DELETE a specific department from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
    

    
    @Operation(summary = "GET Department by code REST API",
    		description="get Department REST APIs used to get a specific Employee from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("code/{departmentCode}")
	public DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String deptDto)
	{
		return departmentService.getDepartmentByCode(deptDto);
	}
    
}
