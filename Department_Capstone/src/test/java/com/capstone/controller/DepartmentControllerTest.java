package com.capstone.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capstone.dto.DepartmentDto;
import com.capstone.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentDto = new DepartmentDto(1L, "HR", "Human Resources Department", "HR001");
    }

    @Test
    void testCreateDepartment() throws Exception {
        when(departmentService.createDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(departmentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(departmentDto)));
    }

    @Test
    void testGetAllDepartments() throws Exception {
        List<DepartmentDto> departmentDtos = Arrays.asList(departmentDto);
        when(departmentService.getAllDepartments()).thenReturn(departmentDtos);

        mockMvc.perform(get("/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(departmentDtos)));
    }

    @Test
    void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDto);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(departmentDto)));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        when(departmentService.updateDepartment(any(Long.class), any(DepartmentDto.class))).thenReturn(departmentDto);

        mockMvc.perform(put("/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(departmentDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(departmentDto)));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        when(departmentService.deleteDepartment(1L)).thenReturn("Department successfully deleted with ID 1");

        mockMvc.perform(delete("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Department successfully deleted with ID 1"));
    }

    @Test
    void testGetDepartmentByCode() throws Exception {
        when(departmentService.getDepartmentByCode("HR001")).thenReturn(departmentDto);

        mockMvc.perform(get("/departments/code/HR001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(departmentDto)));
    }
}
