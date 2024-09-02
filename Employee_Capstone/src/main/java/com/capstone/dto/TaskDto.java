package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="TaskDto Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class TaskDto {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Schema(description = "Enter id")
	    private Long id;

	    @NotEmpty(message = "Task title cannot be empty.")
	    @Schema(description = "Enter title")
	    private String title;

	    @NotEmpty(message = "Task description cannot be empty.")
	    @Schema(description = "Enter description")
	    private String description;
	    
	    @NotEmpty
	    @Schema(description = "Enter status")
	    private String status; // e.g., "Pending", "In Progress", "Completed"
	
//	    private Long taskId;
}