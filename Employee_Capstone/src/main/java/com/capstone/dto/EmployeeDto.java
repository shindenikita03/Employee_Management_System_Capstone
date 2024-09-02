package com.capstone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description="employeeDTO Model Info")
@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeDto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Enter id")
    private Long id;
    
    @NotBlank(message = "Name field cannot be empty or null. Please enter a valid value.")
    @Schema(description = "Enter name")
    private String name;
    
    @Email(message = "Email should be valid")
    @Schema(description = "Enter email")
    private String email;
    
    @NotNull(message = "Phone number cannot be null")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    @Schema(description = "Enter phoneNumber")
    private Long phoneNumber;
    
    @NotBlank(message = "Job role cannot be empty")
    @Schema(description = "Enter jobRole")
    private String jobRole;
    
    @Max(value = 10000000L, message = "Salary is too high. Enter a proper value.")
    @Min(value = 999L, message = "Salary is too low. Enter a proper value.")
    @Schema(description = "Enter salary")
    private Double salary;
    
    @Size(min = 2, max = 30, message = "Department name must be between 2 and 30 characters")
    @Schema(description = "Enter departmentCode")
    private String departmentCode;
    
    @Schema(description = "Enter taskid")
    private Long taskId;
    
    @Schema(description = "Enter performanceid")
    private Long performanceId;


}
