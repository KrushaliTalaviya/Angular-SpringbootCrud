package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Customer;

/**
 * <p>This interface is provide the list of method.</p>
 * <p>This all method is implement on CustomerServiceImplementation class.</p>
 * 
 * @author Krushali Talaviya
 * @version openjdk "11.0.16.1"
 * @since April 2023
 *
 */
public interface CustomerService {

	List<Customer> getAllCustomer();

	Customer getCustomerById(int id);

	void deleteCustomer(int id);

	Customer saveCustomer(Customer customer);

	String checkEmailExist(String email, int id );

	String checkMobileNoExists(String mobileNo, int id);

}