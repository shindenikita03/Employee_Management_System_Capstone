package com.capstone.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.entity.Performance;

@ExtendWith(MockitoExtension.class)
public class PerformanceRepositoryTests {

    @Mock
    private PerformanceRepo performanceRepo;

    private Performance performance;

    @BeforeEach
    public void setup() {
        performance = new Performance(1L, "Excellent Performance", "Achieved all targets", 4.5);
    }

    @Test
    @DisplayName("junit testing for find performance by Id")
    public void testFindById() {
        // Given
        given(performanceRepo.findById(1L)).willReturn(Optional.of(performance));

        // When
        Optional<Performance> foundPerformance = performanceRepo.findById(1L);

        // Then
        assertThat(foundPerformance).isPresent();
        assertThat(foundPerformance.get().getTitle()).isEqualTo("Excellent Performance");
        verify(performanceRepo).findById(1L);
    }

    @Test
    @DisplayName("junit tetsing for save performance")
    public void testSavePerformance() {
        // Given
        given(performanceRepo.save(performance)).willReturn(performance);

        // When
        Performance savedPerformance = performanceRepo.save(performance);

        // Then
        assertThat(savedPerformance).isNotNull();
        assertThat(savedPerformance.getId()).isEqualTo(1L);
        verify(performanceRepo).save(performance);
    }

    @Test
    @DisplayName("Junit testing for delete performance by id")
    public void testDeleteById() {
        // When
        performanceRepo.deleteById(1L);

        // Then
        verify(performanceRepo).deleteById(1L);
    }
}