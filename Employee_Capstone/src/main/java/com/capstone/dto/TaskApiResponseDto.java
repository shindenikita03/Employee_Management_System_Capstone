package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="TaskApiResponseDto Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class TaskApiResponseDto {

	@Schema(description = "Enter employee")
	private EmployeeDto employee;
	@Schema(description = "Enter task")
	private TaskDto task;
	
}
