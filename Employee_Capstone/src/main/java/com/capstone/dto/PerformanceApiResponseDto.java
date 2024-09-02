package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description="performanceApiResponseDTO Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class PerformanceApiResponseDto {

	@Schema(description = "Enter Employee")
	private EmployeeDto Employee;
	@Schema(description = "Enter performance")
	private PerformanceDto performance;
	
}
