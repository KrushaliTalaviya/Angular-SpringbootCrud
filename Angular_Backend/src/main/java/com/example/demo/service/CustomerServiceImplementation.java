package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

/**
 * This class is provide a implementation of the CustomerService class method.
 * This method is used to save, get customer, find the customer etc.
 * 
 * @author Krushali Talaviya
 * @version openjdk "11.0.16.1"
 * @since April 2023
 *
 */
@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public Customer getCustomerById(int id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new IllegalArgumentException("Customer not found for id: " + id);
		}
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public String checkEmailExist(String email, int id) {
		if (id > 0) {
			if (!customerRepository.existsByIdNotAndEmail(id, email)) {
				return null;
			} else {
				return "Email already exists";
			}
		} else {
			if (customerRepository.existsByEmail(email)) {
				return "Email is exists";
			} else {
				return null;
			}
		}
	}

	@Override
	public String checkMobileNoExists(String mobileNo, int id) {
		if (id > 0) {
			if (!customerRepository.existsByIdNotAndMobileNo(id, mobileNo)) {
				return null;
			} else {
				return "Mobile number already exists";
			}
		} else {
			if (customerRepository.existsByMobileNo(mobileNo)) {
				return "Mobile number already exists";
			} else {
				return null;
			}
		}
	}

}