package com.capstone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capstone.entity.Department;

@DataJpaTest
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Test
    void givenDepartmentCode_whenFindByDepartmentCode_thenReturnDepartment() {
        // Given
        String departmentCode = "IT";
        Department department = new Department(null, "Information Technology", "IT Department", departmentCode);
        departmentRepo.save(department);

        // When
        Department foundDepartment = departmentRepo.findByDepartmentCode(departmentCode);

        // Then
        assertThat(foundDepartment).isNotNull();
        assertThat(foundDepartment.getDepartmentCode()).isEqualTo(departmentCode);
    }
}