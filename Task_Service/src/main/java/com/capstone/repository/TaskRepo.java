package com.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
