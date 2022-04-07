package com.example.test.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.test.domain.model.Address;
import com.example.test.domain.model.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>{

	@Query(value = " from CustomerAddress where costumerId = :costumerId ")
	List<CustomerAddress> listByCostumerId(@Param("costumerId") Integer costumerId);

	@Query(value = " from CustomerAddress where costumerId = :costumerId and address.addressId = :addressId ")
	CustomerAddress getByCustomerIdAndAddressId(@Param("costumerId")Integer customerId, @Param("addressId") Integer addressId);

	@Modifying
	@Query(value = " delete from CustomerAddress where costumerId = :costumerId and address = :address ")
	void remove(@Param("costumerId") Integer costumerId, @Param("address") Address address);
	
	
}
