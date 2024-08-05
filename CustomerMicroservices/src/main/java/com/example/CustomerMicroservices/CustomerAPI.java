package com.example.CustomerMicroservices;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/Customers")
public class CustomerAPI {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> customerList = new ArrayList<Customer>();

    public CustomerAPI(){
        Customer customer1 = new Customer(1, "Issa", "apple123", "issa@gmail.com");
        customerList.add(customer1);
        Customer customer2 = new Customer(2, "Darius", "pear123", "darius@gmail.com");
        customerList.add(customer2);
        Customer customer3 = new Customer(3, "Peter", "orange123", "peter@gmail.com");
        customerList.add(customer3);
        Customer customer4 = new Customer(4, "Rueben", "leaf123", "rueben@gmail.com");
        customerList.add(customer4);
        Customer customer5 = new Customer(5, "Jerred", "tomato123", "jerred@gmail.com");
        customerList.add(customer5);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id); 
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    



}
