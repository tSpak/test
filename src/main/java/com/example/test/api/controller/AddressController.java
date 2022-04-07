package com.example.test.api.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.domain.model.Address;
import com.example.test.domain.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/customer/{customerId}/address")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Serializable> save(@PathVariable Integer customerId, @RequestBody Address address) {		
		ResponseEntity<Serializable> response = addressService.save(customerId, address);
		return response;
	}
		
	@DeleteMapping("/customer/{customerId}/address")
	public ResponseEntity<Void> delete(@PathVariable Integer customerId, @RequestBody Address address) {
		addressService.delete(customerId, address);
		return ResponseEntity.noContent().build();
	}	
	
	@GetMapping("/customer/{customerId}/listAddresses")
	public ResponseEntity<List<Address>> listAddresses(@PathVariable Integer customerId) {		
		List<Address> addresses = addressService.listByCustomerId(customerId);
		return CollectionUtils.isEmpty(addresses) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(addresses);
	}
	

}
