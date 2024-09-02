package com.capstone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.capstone.dto.AllApiResponnseDto;
import com.capstone.dto.ApiResponseDto;
import com.capstone.dto.DepartmentDto;
import com.capstone.dto.EmployeeDto;
import com.capstone.dto.PerformanceApiResponseDto;
import com.capstone.dto.PerformanceDto;
import com.capstone.dto.TaskApiResponseDto;
import com.capstone.dto.TaskDto;
import com.capstone.entity.Employee;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.EmployeeRepo;
import com.capstone.service.ApiClient;
import com.capstone.service.EmployeeServiceImpl;
import com.capstone.service.PerformanceApiClient;
import com.capstone.service.TaskApiClient;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private ApiClient apiClient;

    @Mock
    private TaskApiClient taskClient;

    @Mock
    private PerformanceApiClient performanceClient;

    @InjectMocks
    private EmployeeServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Helper method to create a consistent Employee object
    private Employee createTestEmployee() {
        return Employee.builder()
                .name("pavan")
                .email("pavan2222@gmail.com")
                .phoneNumber(584594787L)
                .jobRole("Support")
                .salary(15000.0)
                .departmentCode("VG-001")
                .taskId(1L)
                .performanceId(1L)
                .build();
    }

    @Test
    @DisplayName("Test for creating an Employee in DB...")
    public void givenEmployeeDto_whenCreateEmployee_thenReturnEmployeeDto() {
        // Given
        Employee employee = createTestEmployee();
        EmployeeDto employeeDto = new EmployeeDto();

        when(mapper.map(any(EmployeeDto.class), any())).thenReturn(employee);
        when(repository.save(any(Employee.class))).thenReturn(employee);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        EmployeeDto result = service.createEmployee(employeeDto);

        // Then
        assertThat(result).isEqualTo(employeeDto);
        verify(repository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Test for updating an Employee...")
    public void givenEmployeeDto_whenUpdateEmployee_thenReturnUpdatedEmployeeDto() {
        // Given
        Employee existingEmployee = createTestEmployee();
        Employee updatedEmployee = createTestEmployee();
        EmployeeDto employeeDto = new EmployeeDto();

        when(repository.existsById(anyLong())).thenReturn(true);
        when(mapper.map(any(EmployeeDto.class), any())).thenReturn(existingEmployee);
        when(repository.save(any(Employee.class))).thenReturn(updatedEmployee);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        EmployeeDto result = service.updateEmployee(1L, employeeDto);

        // Then
        assertThat(result).isEqualTo(employeeDto);
        verify(repository, times(1)).save(existingEmployee);
    }

    @Test
    @DisplayName("Test for getting all Employees available in DB...")
    public void whenGetAllEmployees_thenReturnEmployeeDtoList() {
        // Given
        List<Employee> employees = List.of(createTestEmployee());
        List<EmployeeDto> employeeDtos = List.of(new EmployeeDto());

        when(repository.findAll()).thenReturn(employees);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDtos.get(0));

        // When
        List<EmployeeDto> result = service.getAllEmployees();

        // Then
        assertThat(result).isEqualTo(employeeDtos);
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test for getting an Employee by given ID from DB ...")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeDto() {
        // Given
        Employee employee = createTestEmployee();
        EmployeeDto employeeDto = new EmployeeDto();

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        EmployeeDto result = service.getEmployeeById(1L);

        // Then
        assertThat(result).isEqualTo(employeeDto);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test for deleting an Employee of given Id from DB...")
    public void givenEmployeeId_whenDeleteEmployee_thenSuccessMessage() {
        // Given
        doNothing().when(repository).deleteById(anyLong());
        when(repository.existsById(anyLong())).thenReturn(true);

        // When
        String result = service.deleteEmployee(1L);

        // Then
        assertThat(result).isEqualTo("Employee is successfully deleted with the ID: 1");
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test for throwing exception when deleting a non-existent Employee in DB...")
    public void givenNonExistentEmployee_whenDeleteEmployee_thenThrowIdNotFound() {
        // Given
        when(repository.existsById(anyLong())).thenReturn(false);

        // When and Then
        assertThatThrownBy(() -> service.deleteEmployee(1L))
            .isInstanceOf(IdNotFound.class)
            .hasMessage("Employee with ID 1 not found.");
    }

    @Test
    @DisplayName("Test for getting Employee by ID or Code of the Department...")
    public void givenEmployeeId_whenGetEmployeeByIdAndCode_thenReturnApiResponseDto() {
        // Given
        Employee employee = createTestEmployee();
        DepartmentDto departmentDto = new DepartmentDto();
        EmployeeDto employeeDto = new EmployeeDto();
        ApiResponseDto apiResponseDto = new ApiResponseDto(employeeDto, departmentDto);

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        when(apiClient.getDepartmentByCode("VG-001")).thenReturn(departmentDto);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        ApiResponseDto result = service.getEmployeeByIdAndCode(1L);

        // Then
        assertThat(result).isEqualTo(apiResponseDto);
        verify(repository, times(1)).findById(1L);
        verify(apiClient, times(1)).getDepartmentByCode("VG-001");
    }

    @Test
    @DisplayName("Test for getting Tasks and details of the given Employee...")
    public void givenEmployeeId_whenGetTaskAndEmployee_thenReturnTaskApiResponseDto() {
        // Given
        Employee employee = createTestEmployee();
        TaskDto taskDto = new TaskDto();
        EmployeeDto employeeDto = new EmployeeDto();
        TaskApiResponseDto taskApiResponseDto = new TaskApiResponseDto(employeeDto, taskDto);

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        when(taskClient.getTaskById(anyLong())).thenReturn(taskDto);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        TaskApiResponseDto result = service.getTaskAndEmployee(1L);

        // Then
        assertThat(result).isEqualTo(taskApiResponseDto);
        verify(repository, times(1)).findById(1L);
        verify(taskClient, times(1)).getTaskById(1L);
    }

    @Test
    @DisplayName("Test for getting Employee details along with his  Performance...")
    public void givenEmployeeId_whenGetEmployeePerformance_thenReturnPerformanceApiResponseDto() {
        // Given
        Employee employee = createTestEmployee();
        PerformanceDto performanceDto = new PerformanceDto();
        EmployeeDto employeeDto = new EmployeeDto();
        PerformanceApiResponseDto performanceApiResponseDto = new PerformanceApiResponseDto(employeeDto, performanceDto);

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        when(performanceClient.getPerformanceById(anyLong())).thenReturn(performanceDto);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        PerformanceApiResponseDto result = service.getEmployeePerformance(1L);

        // Then
        assertThat(result).isEqualTo(performanceApiResponseDto);
        verify(repository, times(1)).findById(1L);
        verify(performanceClient, times(1)).getPerformanceById(1L);
    }

    @Test
    @DisplayName("Test for getting all the details of Employee like tasks,performance,...")
    public void givenEmployeeId_whenGetAllServices_thenReturnAllApiResponseDto() {
        // Given
        Employee employee = createTestEmployee();
        DepartmentDto departmentDto = new DepartmentDto();
        TaskDto taskDto = new TaskDto();
        PerformanceDto performanceDto = new PerformanceDto();
        EmployeeDto employeeDto = new EmployeeDto();
        AllApiResponnseDto allApiResponseDto = new AllApiResponnseDto(employeeDto, departmentDto, taskDto, performanceDto);

        when(repository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        when(apiClient.getDepartmentByCode("VG-001")).thenReturn(departmentDto);
        when(taskClient.getTaskById(anyLong())).thenReturn(taskDto);
        when(performanceClient.getPerformanceById(anyLong())).thenReturn(performanceDto);
        when(mapper.map(any(Employee.class), any())).thenReturn(employeeDto);

        // When
        AllApiResponnseDto result = service.getAllServices(1L);

        // Then
        assertThat(result).isEqualTo(allApiResponseDto);
        verify(repository, times(1)).findById(1L);
        verify(apiClient, times(1)).getDepartmentByCode("VG-001");
        verify(taskClient, times(1)).getTaskById(1L);
        verify(performanceClient, times(1)).getPerformanceById(1L);
    }
}
