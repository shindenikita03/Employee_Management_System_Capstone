package com.capstone.service;

import java.util.List;

import com.capstone.dto.AllApiResponnseDto;
import com.capstone.dto.ApiResponseDto;
import com.capstone.dto.EmployeeDto;
import com.capstone.dto.PerformanceApiResponseDto;
import com.capstone.dto.PerformanceDto;
import com.capstone.dto.TaskApiResponseDto;

public interface EmployeeService{
	
	public EmployeeDto createEmployee(EmployeeDto employeeDto);
	 public List<EmployeeDto> getAllEmployees();
	 public EmployeeDto getEmployeeById(Long id);
	 public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
	 public String deleteEmployee(Long id);
	 
	 public ApiResponseDto getEmployeeByIdAndCode(Long id);

	 public TaskApiResponseDto getTaskAndEmployee(Long id);
	 
	 public PerformanceApiResponseDto getEmployeePerformance(Long id);
	 
	 public AllApiResponnseDto getAllServices(Long id);

}
