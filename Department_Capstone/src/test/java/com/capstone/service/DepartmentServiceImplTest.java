package com.capstone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.capstone.dto.DepartmentDto;
import com.capstone.entity.Department;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.DepartmentRepo;

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepo repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;
    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department(1L, "HR", "Human Resources Department", "HR001");
        departmentDto = new DepartmentDto(1L, "HR", "Human Resources Department", "HR001");
    }

    @Test
    void testCreateDepartment() {
        when(mapper.map(departmentDto, Department.class)).thenReturn(department);
        when(repository.save(department)).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.createDepartment(departmentDto);
        assertNotNull(result);
        assertEquals(departmentDto.getName(), result.getName());
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = Arrays.asList(department);
        List<DepartmentDto> departmentDtos = Arrays.asList(departmentDto);

        when(repository.findAll()).thenReturn(departments);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        List<DepartmentDto> result = departmentService.getAllDepartments();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(departmentDtos.get(0).getName(), result.get(0).getName());
    }

    @Test
    void testGetDepartmentById() {
        when(repository.findById(1L)).thenReturn(Optional.of(department));
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentById(1L);
        assertNotNull(result);
        assertEquals(departmentDto.getName(), result.getName());
    }

    @Test
    void testUpdateDepartment() {
        when(repository.existsById(1L)).thenReturn(true);
        when(mapper.map(departmentDto, Department.class)).thenReturn(department);
        when(repository.save(department)).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.updateDepartment(1L, departmentDto);
        assertNotNull(result);
        assertEquals(departmentDto.getName(), result.getName());
    }

    @Test
    void testDeleteDepartment() {
        when(repository.existsById(1L)).thenReturn(true);

        String result = departmentService.deleteDepartment(1L);
        assertEquals("Department successfully deleted with ID 1", result);
    }

    @Test
    void testGetDepartmentByCode() {
        when(repository.findByDepartmentCode("HR001")).thenReturn(department);
        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentByCode("HR001");
        assertNotNull(result);
        assertEquals(departmentDto.getDepartmentCode(), result.getDepartmentCode());
    }

    @Test
    void testGetDepartmentById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        
        assertThrows(IdNotFound.class, () -> departmentService.getDepartmentById(2L));
    }
}
