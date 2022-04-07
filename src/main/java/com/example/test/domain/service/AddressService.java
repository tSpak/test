package com.example.test.domain.service;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.domain.model.Address;
import com.example.test.domain.model.CustomerAddress;
import com.example.test.domain.repository.AddressRepository;
import com.example.test.domain.repository.CustomerAddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;	
	
	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@Transactional
	public ResponseEntity<Serializable> save(Integer customerId, Address address) {		
		
		String validationResult = validate(address);
		
		if(!validationResult.equalsIgnoreCase("OK")) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(validationResult);
		}	
				
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setCostumerId(customerId);
		
		Address existedAddress = addressRepository.getByZipCodeAndNumber(address.getZipCode(), address.getNumber());
		if(existedAddress != null) {
			customerAddress.setAddress(existedAddress);			
			CustomerAddress existedCustomerAddress = customerAddressRepository.getByCustomerIdAndAddressId(customerId, existedAddress.getAddressId());
			if(existedCustomerAddress != null) {
				return ResponseEntity.ok().body(existedCustomerAddress.getAddress());
			}
		}else {
			addressRepository.save(address);
			customerAddress.setAddress(address);
		}
		
		customerAddressRepository.save(customerAddress);
		
		return ResponseEntity.ok().body(customerAddress.getAddress());
	}
	
	
	private String validate(Address address) {
		String result = "OK";
		
		String regex = "^[0-9]{5}(?:-[0-9]{3})?$";
		
		Pattern pattern = Pattern.compile(regex);		

		if(address.getZipCode() == null || !pattern.matcher(address.getZipCode()).matches()) {
			return "Please enter a valid ZIP Code.";
		}
	
		return result;
	}
	
	
	public void delete(Integer customerId, Address address) {
		customerAddressRepository.remove(customerId, address);
	}


	public List<Address> listByCustomerId(Integer customerId) {
		return addressRepository.listByCustomerId(customerId);
	}

}
