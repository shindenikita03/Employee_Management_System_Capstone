package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="performanceDTO Model Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {
	
	@Schema(description = "Enter id")
	 private Long id;

	    @NotBlank(message = "Performance title cannot be empty")
	    @Schema(description = "Enter title")
	    private String title;

	    @Schema(description = "Enter description")
	    private String description;
	    
	    @NotNull(message = "Rating Can't be kept Null...")
	    @Min(value = 0, message = "Rating can be Given at least 0")
	    @Max(value = 5, message = "Rating can be Given at most 5")
	    @Schema(description = "Enter rating")
	    private Double rating;

}
