package com.capstone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.capstone.controller.EmployeeController;
import com.capstone.dto.AllApiResponnseDto;
import com.capstone.dto.ApiResponseDto;
import com.capstone.dto.EmployeeDto;
import com.capstone.dto.PerformanceApiResponseDto;
import com.capstone.dto.TaskApiResponseDto;
import com.capstone.service.EmployeeService;

@SpringJUnitConfig
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeServices;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeServices.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.createEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeDto> employeeDtos = Arrays.asList(new EmployeeDto(), new EmployeeDto());
        when(employeeServices.getAllEmployees()).thenReturn(employeeDtos);

        ResponseEntity<List<EmployeeDto>> response = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDtos, response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeServices.getEmployeeById(1L)).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeServices.updateEmployee(eq(1L), any(EmployeeDto.class))).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(1L, employeeDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeServices.deleteEmployee(1L)).thenReturn("Employee deleted successfully");

        ResponseEntity<String> response = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted successfully", response.getBody());
    }

    @Test
    public void testGetEmployeeByCode() {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        when(employeeServices.getEmployeeByIdAndCode(1L)).thenReturn(apiResponseDto);

        ResponseEntity<ApiResponseDto> response = employeeController.getMethodName(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(apiResponseDto, response.getBody());
    }

    @Test
    public void testGetEmployeeAndTask() {
        TaskApiResponseDto taskApiResponseDto = new TaskApiResponseDto();
        when(employeeServices.getTaskAndEmployee(1L)).thenReturn(taskApiResponseDto);

        ResponseEntity<TaskApiResponseDto> response = employeeController.getEmployeeAndTask(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskApiResponseDto, response.getBody());
    }

    @Test
    public void testGetEmployeeAndPerformance() {
        PerformanceApiResponseDto performanceApiResponseDto = new PerformanceApiResponseDto();
        when(employeeServices.getEmployeePerformance(1L)).thenReturn(performanceApiResponseDto);

        ResponseEntity<PerformanceApiResponseDto> response = employeeController.getEmployeeAndPerformance(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(performanceApiResponseDto, response.getBody());
    }

    @Test
    public void testGetAllService() {
        AllApiResponnseDto allApiResponnseDto = new AllApiResponnseDto();
        when(employeeServices.getAllServices(1L)).thenReturn(allApiResponnseDto);

        ResponseEntity<AllApiResponnseDto> response = employeeController.getAllService(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allApiResponnseDto, response.getBody());
    }
}
