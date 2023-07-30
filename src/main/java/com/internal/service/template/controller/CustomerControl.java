package com.internal.service.template.controller;

import com.internal.service.template.exception.EntityNotFoundException;
import com.internal.service.template.model.Customer;
import com.internal.service.template.model.User;
import com.internal.service.template.repository.CustomerRepository;
import com.internal.service.template.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerControl extends ParentControl {

	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerControl.class);

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/all")
	public List<Customer> getAllCustomer() {
		LOGGER.info("/customer received GET /all mapped to getAllCustomer");
		List<Customer> allCustomers = customerRepository.findAll();
		return allCustomers;
	}
}
