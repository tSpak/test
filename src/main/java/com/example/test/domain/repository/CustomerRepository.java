package com.example.test.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.test.domain.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value = " select c.* FROM CUSTOMER c "
			+ " inner join CUSTOMER_ADDRESS cadr "
			+ "	on cadr.CUSTOMER_ID = c.CUSTOMER_ID "
			+ " inner join ADDRESS a "
			+ "	on a.ADDRESS_ID = cadr.ADDRESS_ID "
			+ " where a.ZIP_CODE = :zipCode   ", 
			nativeQuery = true)
	List<Customer> listByZipCode(@Param("zipCode") String zipCode);

	@Query(value = " from Customer where documentId = :documentId ")
	Customer getByDocumentId(@Param("documentId") String documentId);

}
