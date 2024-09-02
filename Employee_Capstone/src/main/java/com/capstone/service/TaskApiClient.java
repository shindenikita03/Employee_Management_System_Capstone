package com.capstone.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.dto.TaskDto;

@FeignClient("TASK-CAPSTONE")
public interface TaskApiClient {

	 @GetMapping("tasks/{id}")
	    public TaskDto getTaskById(@PathVariable("id") Long id);
	
}
