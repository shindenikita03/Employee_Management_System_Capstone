package com.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
	public Department findByDepartmentCode(String departmentCode);
}
