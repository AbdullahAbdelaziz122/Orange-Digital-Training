package com.taskMangement.repository;

import com.taskMangement.models.Customer;
import com.taskMangement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository<Task,Long> {
    Optional<Task> findTaskByTitleAndCustomer(String title, Customer customer);
    Optional<Task> findTasksByIdAndCustomer_Id(Long taskId, Long customerId);
    Optional<Task> findTaskByDescriptionAndCustomer_Id (String description, Long customerId);
    List<Task> findTasksByStatusAndCustomer_Id(String status, Long customerId);
    List<Task>findAllByCustomer_Id(Long customerId);
    List<Task> findTasksByStatusAndDueDateAndCustomer_Id(String status, LocalDate dueDate, Long customerId);
    List<Task> findTasksByDueDateAndCustomer_Id(LocalDate dueDate, Long customerId);


}
