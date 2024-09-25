package com.taskMangement.controllers;

import com.taskMangement.models.Customer;
import com.taskMangement.repository.CustomerRepository;
import com.taskMangement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody  Customer customer){


     try{
         String hashPassword = passwordEncoder.encode(customer.getPassword());
         customer.setPassword(hashPassword);

         Customer savedCustomer = customerRepository.save(customer);

         if(savedCustomer.getId() > 0){
             return new ResponseEntity<String>("User has been created successfully", HttpStatus.CREATED);
         }else {
             return new ResponseEntity<String>("User Registration has been failed", HttpStatus.BAD_REQUEST);
         }
     }catch (Exception ex){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred: " + ex.getMessage());
     }







    }



    @GetMapping("/admin/customers/search")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "customerId", required = false) Long customerId
    ) {


        if (username == null && customerId != null) {
            List<Customer> customer = customerRepository.findById(customerId).stream().toList();
            return new ResponseEntity<List<Customer>>(customer, HttpStatus.FOUND);
        } else if (username != null && customerId == null) {
            List<Customer> customer = customerRepository.findByUsername(username).stream().toList();
            return new ResponseEntity<List<Customer>>(customer, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
        }
    }



    // Update

    @PutMapping("/admin/customer/update")
    public ResponseEntity<Customer> updateCustomer(Customer customer, Long customerId){
       return new ResponseEntity<>(customerService.updateCustomer(customer, customerId), HttpStatus.ACCEPTED);
    }
    // Delete

    @DeleteMapping("/admin/customer/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<String>("User has been deleted", HttpStatus.OK);
    }

}
