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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.domain.model.Address;
import com.example.test.domain.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Serializable> save(@RequestParam Integer customerId, @RequestBody Address address) {		
		ResponseEntity<Serializable> response = addressService.save(customerId, address);
		return response;
	}
		
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestParam Integer customerId, @RequestBody Address address) {
		addressService.delete(customerId, address);
		return ResponseEntity.noContent().build();
	}	
	
	@GetMapping("/listByCustomerId")
	public ResponseEntity<List<Address>> listByCustomerId(@RequestParam Integer customerId) {		
		List<Address> addresses = addressService.listByCustomerId(customerId);
		return CollectionUtils.isEmpty(addresses) ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(addresses);
	}
	

}
