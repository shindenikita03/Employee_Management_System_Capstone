package com.capstone.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

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

import com.capstone.dto.PerformanceDto;
import com.capstone.service.PerformanceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PerformanceController.class)
public class PerformanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerformanceService performanceService;

    @Autowired
    private ObjectMapper objectMapper;

    private PerformanceDto performanceDto;

    @BeforeEach
    public void setup() {
        performanceDto = new PerformanceDto(1L, "Excellent Performance", "Achieved all targets", 4.5);
    }

    @Test
    public void testCreatePerformance() throws Exception {
        when(performanceService.createPerformance(any(PerformanceDto.class))).thenReturn(performanceDto);

        mockMvc.perform(post("/api/performance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(performanceDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Excellent Performance"));
    }

    @Test
    public void testGetAllPerformances() throws Exception {
        when(performanceService.getAllPerformances()).thenReturn(Arrays.asList(performanceDto));

        mockMvc.perform(get("/api/performance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Excellent Performance"));
    }

    @Test
    public void testGetPerformanceById() throws Exception {
        when(performanceService.getPerformanceById(1L)).thenReturn(performanceDto);

        mockMvc.perform(get("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Excellent Performance"));
    }

    @Test
    public void testUpdatePerformance() throws Exception {
        when(performanceService.updatePerformance(eq(1L), any(PerformanceDto.class))).thenReturn(performanceDto);

        mockMvc.perform(put("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(performanceDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Excellent Performance"));
    }

    @Test
    public void testDeletePerformance() throws Exception {
        when(performanceService.deletePerformance(1L)).thenReturn("Performance successfully deleted with ID 1");

        mockMvc.perform(delete("/api/performance/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Performance successfully deleted with ID 1"));
    }
}
