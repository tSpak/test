package com.example.test.api.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.domain.model.Customer;
import com.example.test.domain.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<Serializable> save(@RequestBody Customer customer) {		
		 ResponseEntity<Serializable> response = customerService.save(customer);
		return response;
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<Serializable> update(@RequestBody Customer customer) {
		 ResponseEntity<Serializable> response = customerService.save(customer);
		return response;
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody Customer customer) {
		customerService.delete(customer);
		return ResponseEntity.noContent().build();
	}	
	
	@GetMapping("/listByZipCode")
	public ResponseEntity<List<Customer>> listByZipCode(@RequestParam String zipCode) {		
		List<Customer> customers = customerService.listByZipCode(zipCode);
		return CollectionUtils.isEmpty(customers) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customers);
	}
	
	@GetMapping("/getByDocumentId")
	public ResponseEntity<Customer> getByDocumentId(@RequestParam String documentId) {		
		Customer customer = customerService.getByDocumentId(documentId);
		return customer == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(customer);
	}
	

}
