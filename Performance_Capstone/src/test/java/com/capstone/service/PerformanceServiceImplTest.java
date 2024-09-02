package com.capstone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.capstone.dto.PerformanceDto;
import com.capstone.entity.Performance;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.PerformanceRepo;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceImplTest {

    @Mock
    private PerformanceRepo performanceRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    private Performance performance;
    private PerformanceDto performanceDto;

    @BeforeEach
    public void setup() {
        performance = new Performance(1L, "Excellent Performance", "Achieved all targets", 4.5);
        performanceDto = new PerformanceDto(1L, "Excellent Performance", "Achieved all targets", 4.5);
    }

    @Test
    public void testCreatePerformance() {
        when(modelMapper.map(performanceDto, Performance.class)).thenReturn(performance);
        when(performanceRepo.save(performance)).thenReturn(performance);
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        PerformanceDto savedPerformance = performanceService.createPerformance(performanceDto);

        assertThat(savedPerformance).isNotNull();
        assertThat(savedPerformance.getId()).isEqualTo(performance.getId());
        verify(performanceRepo, times(1)).save(performance);
    }

    @Test
    public void testGetAllPerformances() {
        Performance performance2 = new Performance(2L, "Good Performance", "Met most of the targets", 3.8);
        List<Performance> performances = Arrays.asList(performance, performance2);
        when(performanceRepo.findAll()).thenReturn(performances);
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);
        when(modelMapper.map(performance2, PerformanceDto.class))
            .thenReturn(new PerformanceDto(2L, "Good Performance", "Met most of the targets", 3.8));

        List<PerformanceDto> performanceDtoList = performanceService.getAllPerformances();

        assertThat(performanceDtoList.size()).isEqualTo(2);
        verify(performanceRepo, times(1)).findAll();
    }

    @Test
    public void testGetPerformanceById_Success() {
        when(performanceRepo.findById(1L)).thenReturn(Optional.of(performance));
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        PerformanceDto foundPerformance = performanceService.getPerformanceById(1L);

        assertThat(foundPerformance).isNotNull();
        assertThat(foundPerformance.getId()).isEqualTo(performance.getId());
    }

    @Test
    public void testGetPerformanceById_IdNotFound() {
        when(performanceRepo.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IdNotFound.class, () -> performanceService.getPerformanceById(2L));
    }

    @Test
    public void testUpdatePerformance_Success() {
        when(performanceRepo.existsById(1L)).thenReturn(true);
        when(modelMapper.map(performanceDto, Performance.class)).thenReturn(performance);
        when(performanceRepo.save(performance)).thenReturn(performance);
        when(modelMapper.map(performance, PerformanceDto.class)).thenReturn(performanceDto);

        PerformanceDto updatedPerformance = performanceService.updatePerformance(1L, performanceDto);

        assertThat(updatedPerformance).isNotNull();
        assertThat(updatedPerformance.getId()).isEqualTo(performance.getId());
        verify(performanceRepo, times(1)).save(performance);
    }

    @Test
    public void testUpdatePerformance_IdNotFound() {
        when(performanceRepo.existsById(2L)).thenReturn(false);

        assertThrows(IdNotFound.class, () -> performanceService.updatePerformance(2L, performanceDto));
    }

    @Test
    public void testDeletePerformance_Success() {
        when(performanceRepo.existsById(1L)).thenReturn(true);

        String result = performanceService.deletePerformance(1L);

        assertThat(result).isEqualTo("Performance successfully deleted with ID 1");
        verify(performanceRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePerformance_IdNotFound() {
        when(performanceRepo.existsById(2L)).thenReturn(false);

        assertThrows(IdNotFound.class, () -> performanceService.deletePerformance(2L));
    }
}
