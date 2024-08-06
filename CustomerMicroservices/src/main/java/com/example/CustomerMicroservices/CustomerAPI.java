package com.example.CustomerMicroservices;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ArrayNode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CustomerAPI {

    @Autowired
    CustomerRepository customerRepository;

    ArrayList<Customer> customerList = new ArrayList<Customer>();

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

        customerRepository.saveAll(customerList);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") int id){
        for(Customer c : customerList){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, UriComponentsBuilder uri) {
		if (customer.getId() != 0 || customer.getName() == null || customer.getEmail() == null) {
			// Reject we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		customer = customerRepository.save(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}


    @PutMapping("/customers/{id}")
	public ResponseEntity<?> putCustomer(
			@RequestBody Customer newCustomer,
			@PathVariable("id") int customerId) 
	{
		if (newCustomer.getId() != customerId || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer = customerRepository.save(newCustomer);
		return ResponseEntity.ok().build();
	}	
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") int id) {
		// repo.delete(id);
		customerRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}	
	

}