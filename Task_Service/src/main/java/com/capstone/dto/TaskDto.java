package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="TaskDto Model Info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

	 @Schema(description = "Enter id")
    private Long id;

    @NotEmpty(message = "Task title cannot be empty.")
    @Schema(description = "Enter title")
    private String title;

    @NotEmpty(message = "Task description cannot be empty.")
    @Schema(description = "Enter description")
    private String description;

    @NotEmpty(message = "Task description cannot be empty.")
    @Schema(description = "Enter status")
    private String status; // e.g., "Pending", "In Progress", "Completed"
}