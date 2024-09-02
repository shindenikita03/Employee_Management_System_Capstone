package com.capstone.repository;

import static org.assertj.core.api.Assertions.assertThat
;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capstone.entity.Employee;
import com.capstone.repository.EmployeeRepo;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepo repository;

    @Test
    @DisplayName("Test for Creating the Employee and saving it into DB...")
    public void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // Given - setup for our test
        Employee pavan = Employee.builder()
            .name("pavan")
            .email("pavan2222@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("Support")
            .salary(15000.0)
            .departmentCode("VG-001")
            .taskId(1L)
            .performanceId(1L)
            .build();

        // When
        Employee savedEmployee = repository.save(pavan);

        // Then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull(); // ID should be generated
        assertThat(savedEmployee.getName()).isEqualTo("pavan");
    }

    @Test
    @DisplayName("Test for Fetching all Employees from DB...")
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        // Given - setup for our test
        Employee salman = Employee.builder()
            .name("salman")
            .email("salman12@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("Actor")
            .salary(1005000.0)
            .departmentCode("BO-001")
            .taskId(1L)
            .performanceId(1L)
            .build();

        Employee soma = Employee.builder()
            .name("soma")
            .email("soma512@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("student")
            .salary(2500.0)
            .departmentCode("ST-001")
            .taskId(1L)
            .performanceId(1L)
            .build();

        repository.save(salman);
        repository.save(soma);

        // When
        List<Employee> employeelist = repository.findAll();

        // Then
        assertThat(employeelist).isNotNull();
        assertThat(employeelist.size()).isEqualTo(2);
        assertThat(employeelist).extracting(Employee::getName).contains("salman", "soma");
    }
    
    @Test
    @DisplayName("Test for fetching the Employee data of given Id from DB...")
    public void givenEmployee_whenFindById_thenReturnEmployee() {
        // Given - setup for our test
        Employee pavan = Employee.builder()
            .name("pavan")
            .email("pavan2222@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("Support")
            .salary(15000.0)
            .departmentCode("VG-001")
            .taskId(1L)
            .performanceId(1L)
            .build();
        
        Employee savedEmployee = repository.save(pavan);

        // When
        Optional<Employee> emp = repository.findById(savedEmployee.getId());

        // Then
        assertThat(emp).isPresent(); // Check if the employee is present
        assertThat(emp.get()).isNotNull(); // Retrieve the employee object
        assertThat(emp.get().getId()).isGreaterThan(0); // Check that the ID is greater than 0
    }
    
    @Test
    @DisplayName("Test for Updating the Employee of given Id in DB...")
    public void givenEmployee_whenFindById_thenReturnUpdatedEmployee() {
        // Given - setup for our test
        Employee pavan = Employee.builder()
            .name("pavan")
            .email("pavan2222@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("Support")
            .salary(15000.0)
            .departmentCode("VG-001")
            .taskId(1L)
            .performanceId(1L)
            .build();
        
        // When
        Employee savedEmployee = repository.save(pavan);
        savedEmployee.setName("Vijay");
        Employee updatedEmployee = repository.save(savedEmployee);
        
        // Then
        assertThat(updatedEmployee.getName()).isEqualTo("Vijay");
    }
    
    
    @Test
    @DisplayName("Test for Deleting the Employee of given Id from DB...")
    public void givenEmployee_whenFindById_thenDeleteThatEmployee() {
        // Given - setup for our test
        Employee pavan = Employee.builder()
            .name("pavan")
            .email("pavan2222@gmail.com")
            .phoneNumber(584594787L)
            .jobRole("Support")
            .salary(15000.0)
            .departmentCode("VG-001")
            .taskId(1L)
            .performanceId(1L)
            .build();
 
        Employee savedEmployee = repository.save(pavan);
        
        //when
        repository.delete(savedEmployee);
        
        Optional<Employee> Optional =repository.findById(savedEmployee.getId());
       
    }
    
    
}
