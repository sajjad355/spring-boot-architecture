package com.internal.service.template.controller;

import com.internal.service.template.model.Customer;
import com.internal.service.template.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends ParentController {

	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/all")
	public List<Customer> getAllCustomer() {
		LOGGER.info("/customer received GET /all mapped to getAllCustomer");
		List<Customer> allCustomers = customerRepository.findAll();
		return allCustomers;
	}
}
