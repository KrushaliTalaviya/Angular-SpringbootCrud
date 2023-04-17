package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ErrorConstantMessages;

/**
 * This class is a controller class. Using this class we can handle the request
 * and give the response.
 * 
 * @author Krushali Talaviya
 * @version openjdk "11.0.16.1"
 * @since April 2023
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = { "/", "/customerList" })
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@GetMapping("/updateCustomer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") int id, Model model) {
		try {
			return customerService.getCustomerById(id);
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/addNewCustomer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		String emailMessage;
		String mobileMessage;
		int id = (customer.getId() == null ? 0 : customer.getId());
		emailMessage = customerService.checkEmailExist(customer.getEmail(), id);
		mobileMessage = customerService.checkMobileNoExists(customer.getMobileNo(), id);
		if (emailMessage != null || mobileMessage != null) {
			Customer customerError = new Customer();
			if (emailMessage != null) {
				customerError.setEmail(ErrorConstantMessages.EMAIL_EXISTS);
			}
			if (mobileMessage != null) {
				customerError.setMobileNo(ErrorConstantMessages.MOBILE_EXISTS);
			}
			customerError.setError(true);
			return customerError;
		} else {
			return customerService.saveCustomer(customer);
		}
	}

	@DeleteMapping("/deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable(value = "id") int id) {
		customerService.deleteCustomer(id);
	}

}