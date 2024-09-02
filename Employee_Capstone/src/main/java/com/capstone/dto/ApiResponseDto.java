package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="ApiResponseDTO Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class ApiResponseDto {
	


	@Schema(description = "Enter employee")
	private EmployeeDto employee;
	@Schema(description = "Enter department")
	private DepartmentDto department;
	
}
