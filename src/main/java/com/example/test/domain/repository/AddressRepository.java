package com.example.test.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.test.domain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Query(value = " from Address where zipCode = :zipCode  and number = :number")
	Address getByZipCodeAndNumber(@Param("zipCode") String zipCode, @Param("number") Integer number);

	@Query(value = " select a.* "
			+ " from address a "
			+ " inner join customer_address cadr"
			+ " on a.address_id = cadr.address_id "
			+ " where cadr.customer_id = :customerId", nativeQuery = true)
	List<Address> listByCustomerId(@Param("customerId") Integer customerId);

}
