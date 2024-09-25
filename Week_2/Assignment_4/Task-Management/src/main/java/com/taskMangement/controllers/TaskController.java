package com.taskMangement.controllers;

import com.taskMangement.exceptions.ResourceNotFoundException;
import com.taskMangement.models.Customer;
import com.taskMangement.models.Task;
import com.taskMangement.payload.TaskDTO;
import com.taskMangement.repository.CustomerRepository;
import com.taskMangement.repository.TaskRepository;
import com.taskMangement.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @PostMapping("/user/task/create/{customerId}")
    public ResponseEntity<TaskDTO> createTask(
            @RequestBody Task task,
            @PathVariable Long customerId
    )
    {
        TaskDTO savedTask = taskService.createTask(task, customerId);
        return new ResponseEntity<TaskDTO>(savedTask, HttpStatus.CREATED);
    }




    @GetMapping("/user/task/customer/{customerId}")
    public ResponseEntity<?> getTasksOrSearchByTitleOrDescription(
            @PathVariable Long customerId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dueDate", required = false) LocalDate dueDate,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description) {

        try {
            // Verify if customer exists
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

            // Handle search by title or description
            if (title != null && description != null) {
                throw new BadRequestException("You can't search by both title and description at the same time");
            } else if (title != null) {
                TaskDTO task = taskService.findTaskByTitle(title, customerId);
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else if (description != null) {
                TaskDTO task = taskService.findTaskByDescription(description, customerId);
                return new ResponseEntity<>(task, HttpStatus.OK);
            }

            // Handle search by status and/or due date
            List<TaskDTO> tasks;
            if (status != null && dueDate != null) {
                tasks = taskRepository.findTasksByStatusAndDueDateAndCustomer_Id(status, dueDate, customerId)
                        .stream()
                        .map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList();
            } else if (status != null) {
                tasks = taskRepository.findTasksByStatusAndCustomer_Id(status, customerId)
                        .stream()
                        .map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList();
            } else if (dueDate != null) {
                tasks = taskRepository.findTasksByDueDateAndCustomer_Id(dueDate, customerId)
                        .stream()
                        .map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList();
            } else {
                tasks = taskRepository.findAllByCustomer_Id(customerId)
                        .stream()
                        .map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList();
            }

            // Return tasks or empty list
            return new ResponseEntity<>(tasks, HttpStatus.OK);

        } catch (Exception ex) {
            throw new RuntimeException("An error has occurred: " + ex.getMessage());
        }
    }



    @PutMapping("/user/task/update/{customerId}/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(
            @RequestBody(required = false) Task task,
            @PathVariable(value = "taskId") Long taskId,
            @PathVariable(value = "customerId") Long customerId,
            @RequestParam(value = "status", required = false) String status
    ) {
        try {

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

            Task existingTask = taskRepository.findTasksByIdAndCustomer_Id(taskId, customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("User doesn't have this task"));


            if (status != null) {
                existingTask.setStatus(status);
            }


            if (task != null) {

                if (task.getTitle() != null) existingTask.setTitle(task.getTitle());
                if (task.getDescription() != null) existingTask.setDescription(task.getDescription());
                if (task.getDueDate() != null) existingTask.setDueDate(task.getDueDate());
                existingTask.setCustomer(customer);
            }


            Task savedTask = taskRepository.save(existingTask);


            TaskDTO taskDTO = modelMapper.map(savedTask, TaskDTO.class);

            return new ResponseEntity<>(taskDTO, HttpStatus.OK);

        } catch (Exception exception) {
            throw new RuntimeException("An error occurred: " + exception.getMessage());
        }
    }





    @DeleteMapping("/user/task/delete")
    public ResponseEntity<String> deleteTask(@RequestParam(value = "taskId", required = true) Long taskId){

        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }


}