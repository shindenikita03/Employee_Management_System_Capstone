package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="DepartmentDTO Model Info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    
	@Schema(description = "Enter id")
    private Long id;

	 @Schema(description = "Enter name")
    @NotEmpty(message = "Department name cannot be empty.")
    private String name;
	 @Schema(description = "Enter description")
    @NotEmpty(message = "Department description cannot be empty.")
    @Size(max = 200, message = "Description cannot be more than 200 characters.")
    private String description;
    
	 @Schema(description = "Enter departmentCode")
    @NotEmpty(message = "Department code should not be empty")
    private String departmentCode;
}
