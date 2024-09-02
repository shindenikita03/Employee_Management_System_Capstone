package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description="DepartmentDTO Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class DepartmentDto {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Schema(description = "Enter id")
	    private Long id;

	 @Schema(description = "Enter name")
	    @NotEmpty(message = "Department name cannot be empty.")
	    private String name;

	    @NotEmpty(message = "Department description cannot be empty.")
	    @Size(max = 200, message = "Description cannot be more than 200 characters.")
	    @Schema(description = "Enter description")
	    private String description;
	    
	    @NotEmpty(message= "Department shouldn't be empty.")
	    @Schema(description = "Enter departmentCode")
	    private String departmentCode;

}
