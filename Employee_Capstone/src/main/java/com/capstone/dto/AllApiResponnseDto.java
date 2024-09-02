package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description="AllApiResponseDTO Model Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllApiResponnseDto {

	@Schema(description = "Enter employee")
	private EmployeeDto employee;
	@Schema(description = "Enter department")
	private DepartmentDto department;
	@Schema(description = "Enter task")
	private TaskDto task;
	@Schema(description = "Enter performance")
	private PerformanceDto performance;
	
}