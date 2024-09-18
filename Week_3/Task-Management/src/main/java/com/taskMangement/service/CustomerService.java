package com.taskMangement.service;

import com.taskMangement.models.Customer;
import com.taskMangement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    // Create
    // this is the register method
    // Read
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerByUsername(String username){
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: "+ username+" are not found"));
    }





    // Update

    public Customer updateCustomer(Customer customer, Long customerId){
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UsernameNotFoundException("User are not found"));

        customer.setId(customerId);
        String hashedPassword =passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        return customerRepository.save(customer);
    }
    // Delete


    public void deleteCustomer(Long customerId){
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UsernameNotFoundException("User are not found"));

        customerRepository.delete(existingCustomer);
    }






}
