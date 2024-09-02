package com.capstone.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.capstone.entity.Task;
import com.capstone.repository.TaskRepo;

import java.util.List;

@DataJpaTest
public class TaskRepoTest {

    @Autowired
    private TaskRepo repository;

    @Test
    @DisplayName("JUnit test for saving the task operation")
    public void givenTaskObject_whenSave_thenReturnSavedTaskObject() {
        // given - precondition or setup
        Task task = Task.builder()
                        .title("Push code to Testing environment")
                        .description("Code post to testing environment")
                        .status("Pending")
                        .build();

        // when - action or the behaviour that we are going to test
        Task savedTask = repository.save(task);

        // then - verify the output
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isGreaterThan(0);
        assertThat(savedTask.getTitle()).isEqualTo("Push code to Testing environment");
        assertThat(savedTask.getDescription()).isEqualTo("Code post to testing environment");
        assertThat(savedTask.getStatus()).isEqualTo("Pending");
    }

    @Test
    @DisplayName("JUnit test for finding a task by ID")
    public void givenExistingTask_whenFindById_thenReturnTask() {
        // given
        Task task = Task.builder()
                        .title("Complete documentation")
                        .description("Write and review the documentation")
                        .status("In Progress")
                        .build();
        Task savedTask = repository.save(task);

        // when
        Task foundTask = repository.findById(savedTask.getId()).orElse(null);

        // then
        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getId()).isEqualTo(savedTask.getId());
        assertThat(foundTask.getTitle()).isEqualTo("Complete documentation");
        assertThat(foundTask.getDescription()).isEqualTo("Write and review the documentation");
        assertThat(foundTask.getStatus()).isEqualTo("In Progress");
    }

    @Test
    @DisplayName("Junit test for finding all tasks")
    public void givenTasks_whenFindAll_thenReturnTasksList() {
        // given
        Task task1 = Task.builder()
                         .title("Task 1")
                         .description("Description 1")
                         .status("Pending")
                         .build();
        Task task2 = Task.builder()
                         .title("Task 2")
                         .description("Description 2")
                         .status("Completed")
                         .build();
        repository.save(task1);
        repository.save(task2);
        
        // when
        List<Task> tasks = (List<Task>) repository.findAll();

        // then
        assertThat(tasks).hasSize(2);
        assertThat(tasks).extracting(Task::getTitle).containsExactlyInAnyOrder("Task 1", "Task 2");
    }
}
