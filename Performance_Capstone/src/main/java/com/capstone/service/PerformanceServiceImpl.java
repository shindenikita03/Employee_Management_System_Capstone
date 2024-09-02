package com.capstone.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capstone.dto.PerformanceDto;
import com.capstone.entity.Performance;
import com.capstone.exception.IdNotFound;
import com.capstone.repository.PerformanceRepo;

@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepo repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PerformanceDto createPerformance(PerformanceDto performanceDto) {
        Performance performance = mapper.map(performanceDto, Performance.class);
        Performance savedPerformance = repository.save(performance);
        return mapper.map(savedPerformance, PerformanceDto.class);
    }

    @Override
    public List<PerformanceDto> getAllPerformances() {
        List<Performance> performances = repository.findAll();
        return performances.stream()
                           .map(performance -> mapper.map(performance, PerformanceDto.class))
                           .collect(Collectors.toList());
    }

    @Override
    public PerformanceDto getPerformanceById(Long id) {
        Performance performance = repository.findById(id)
            .orElseThrow(() -> new IdNotFound("Performance with ID " + id + " not found."));
        return mapper.map(performance, PerformanceDto.class);
    }

    @Override
    public PerformanceDto updatePerformance(Long id, PerformanceDto performanceDto) {
        if (!repository.existsById(id)) {
            throw new IdNotFound("Performance with ID " + id + " not found.");
        }
        Performance performance = mapper.map(performanceDto, Performance.class);
        performance.setId(id);
        Performance updatedPerformance = repository.save(performance);
        return mapper.map(updatedPerformance, PerformanceDto.class);
    }

    @Override
    public String deletePerformance(Long id) {
        if (!repository.existsById(id)) {
            throw new IdNotFound("Performance with ID " + id + " not found.");
        }
        repository.deleteById(id);
        return "Performance successfully deleted with ID " + id;
    }
}
