package com.example.test.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_address")
public class CustomerAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_address_id")
	private Integer customerAddressId;
	
	@Column(name = "customer_id")
	private Integer costumerId;
	
	@ManyToOne
	@JoinColumn(name="address_id", referencedColumnName ="address_id")
	private Address address;

	public Integer getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(Integer customerAddressId) {
		this.customerAddressId = customerAddressId;
	}


	public Integer getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(Integer costumerId) {
		this.costumerId = costumerId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAddressId == null) ? 0 : customerAddressId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerAddress other = (CustomerAddress) obj;
		if (customerAddressId == null) {
			if (other.customerAddressId != null)
				return false;
		} else if (!customerAddressId.equals(other.customerAddressId))
			return false;
		return true;
	}
	
	
	
	
}
