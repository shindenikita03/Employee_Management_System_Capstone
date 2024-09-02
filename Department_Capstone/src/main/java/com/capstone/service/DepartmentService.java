package com.capstone.service;

import java.util.List;

import com.capstone.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);
    
    List<DepartmentDto> getAllDepartments();
    
    DepartmentDto getDepartmentById(Long id);
    
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    
    DepartmentDto getDepartmentByCode(String code);
    
    String deleteDepartment(Long id);
}
