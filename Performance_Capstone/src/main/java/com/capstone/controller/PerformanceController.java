package com.capstone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capstone.dto.PerformanceDto;
import com.capstone.service.PerformanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name=" CRUD REST APIs for Performance resourse",
description=" CRUD REST APIs- Create performance,update performance,get performance,get all performance,delete performance")
@RestController
@CrossOrigin
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Operation(summary = "CREATE performance REST APIs",
    		description="create performance REST APIs used to save performance in a database")
    
    @ApiResponse(
    		responseCode="201",
    		description="HTTP Status 201 CREATED"
    		)
    @PostMapping
    public ResponseEntity<PerformanceDto> createPerformance(@RequestBody @Valid PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.createPerformance(performanceDto), HttpStatus.CREATED);
    }

    @Operation(summary = "GET ALL performance REST APIs",
    		description="get all performance REST APIs used to get all performances from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping
    public ResponseEntity<List<PerformanceDto>> getAllPerformances() {
        return new ResponseEntity<>(performanceService.getAllPerformances(), HttpStatus.OK);
    }

    @Operation(summary = "GET performance by id REST API",
    		description="get performance REST APIs used to get a specific performance from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDto> getPerformanceById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(performanceService.getPerformanceById(id), HttpStatus.OK);
    }

    @Operation(summary = "UPDATE performance  REST API",
    		description="UPDATE performance REST API is  used to update a specific performance in a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceDto> updatePerformance(@PathVariable("id") Long id, @RequestBody @Valid PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.updatePerformance(id, performanceDto), HttpStatus.OK);
    }

    @Operation(summary = "DELETE performance REST API",
    		description="DELETE performance REST API is used to DELETE a specific performance from a database")
    
    @ApiResponse(
    		responseCode="200",
    		description="HTTP Status 200 SUCCESS"
    		)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformance(@PathVariable("id") Long id) {
        return new ResponseEntity<>(performanceService.deletePerformance(id), HttpStatus.OK);
    }
}
