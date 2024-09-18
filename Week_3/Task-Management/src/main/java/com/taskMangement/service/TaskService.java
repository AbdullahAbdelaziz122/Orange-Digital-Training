package com.taskMangement.service;

import com.taskMangement.exceptions.ResourceNotFoundException;
import com.taskMangement.models.Customer;
import com.taskMangement.models.Task;
import com.taskMangement.payload.TaskDTO;
import com.taskMangement.repository.CustomerRepository;
import com.taskMangement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    public TaskDTO createTask(Task task, Long customerId){
        // check existence
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
            task.setCustomer(customer);
            Task savedtask = taskRepository.save(task);
            return modelMapper.map(savedtask, TaskDTO.class);

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }


    public List<TaskDTO> getAllTasksByCustomer(Long customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));

            return taskRepository.findAllByCustomer_Id(customerId).stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public TaskDTO findTaskByTitle(String title, Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        return modelMapper.map( taskRepository.findTaskByTitleAndCustomer(title, customer), TaskDTO.class);
    }



    public TaskDTO findTaskByDescription(String description, Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        return modelMapper.map( taskRepository.findTaskByDescriptionAndCustomer_Id(description, customerId), TaskDTO.class);
    }










    public TaskDTO updateStatus(String status, Long taskId, Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
        taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
        Task existingTask = taskRepository.findTasksByIdAndCustomer_Id(taskId, customerId).orElseThrow(() -> new ResourceNotFoundException("User doesn't have this task"));
        existingTask.setStatus(status);
        return modelMapper.map(existingTask, TaskDTO.class);
    }




    public String deleteTask(Long taskId){
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
        taskRepository.deleteById(taskId);
        return "Task Has been deleted successfully";
    }







    /*
    * Last thing we stopped at was:
    * We want to make the crud operations for the task entity \
    * We want to figure out why our custom exceptions isn't handled
    * 
    *
    *
    * */
}
