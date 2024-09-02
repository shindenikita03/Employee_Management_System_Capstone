package com.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.entity.Performance;

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
}
