package com.example.test.domain.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.domain.model.Customer;
import com.example.test.domain.repository.CustomerAddressRepository;
import com.example.test.domain.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	public ResponseEntity<Serializable> save(Customer customer) {
		String validationResult = validate(customer);
		if(!validationResult.equalsIgnoreCase("OK")) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(validationResult);
		}	
		
		if(customer.getCustomerId() == null) {
			customer.setCreatedDate(LocalDateTime.now());
		}else {
			customer.setUpdatedDate(LocalDateTime.now());
		}
		
		customer = customerRepository.save(customer);
		return ResponseEntity.ok().body(customer);
	}
	
	
	private String validate(Customer customer) {
		String result = "OK";
		
		Customer customer2 = customerRepository.getByDocumentId(customer.getDocumentId());
		
		if(customer2 != null && !customer2.equals(customer)){	
			return "Document ID already exist.";
		}		
		return result;
	}
	
	@Transactional
	public void delete(Customer customer) {
		customerAddressRepository.removeByCustomer(customer.getCustomerId());
		customerRepository.delete(customer);
	}


	public List<Customer> listByZipCode(String zipCode) {
		return customerRepository.listByZipCode(zipCode);
	}


	public Customer getByDocumentId(String documentId) {
		return customerRepository.getByDocumentId(documentId);
	}
	
	
	

}
