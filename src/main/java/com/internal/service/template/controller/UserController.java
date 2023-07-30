package com.internal.service.template.controller;

import com.internal.service.template.model.User;
import com.internal.service.template.repository.UserRepository;
import com.internal.service.template.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController extends ParentController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
    UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/all")
	public List<User> getAllUsers() {
		LOGGER.info("/userV2 received GET /all mapped to getAllUsers");
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	@PostMapping("/save")
	public User save(@Valid @RequestBody User userData) throws MessagingException {
		LOGGER.info(String.format("/save received request with user %s", userData));
		String userPassword = null;
		String action;
		String auditTrailRecord;
		if (userData.getUserId() > 0) {
			User user = userRepository.findByUserId(userData.getUserId());
			if (user == null) {
				throw new EntityNotFoundException(User.class, "id", String.valueOf(user.getUserId()));
			}
			userPassword = user.getPassword();
			if (userPassword != null) {
				user.setPassword(userPassword);
			}
			action = "Updated user";
		} else {
			action = "insert user";
			auditTrailRecord = userData.toString();
		}
		String password = encoder.encode(userData.getPassword());
		userData.setPassword(password);

		User savedUser = userRepository.save(userData);
		super.saveUserHistory(savedUser.getEditedBy(), "User", action, "");
		return savedUser;
	}



//	@PostMapping("/resetPassword")
//	public UserV2 resetPassword(@Valid @RequestBody UserV2 user) {
//		LOGGER.info(String.format("/resetPassword received request with user %s", user));
//		// check if update scenario and get password and set it in object so as not to
//		// overirde
//		String requestedPassword = user.getPassword();
//		String action = "Reset Password";
//		UserV2 userLookup = userRepository.findByUserId(user.getUserId());
//		if (userLookup == null) {
//			throw new EntityNotFoundException(UserV2.class, "id", String.valueOf(user.getUserId()));
//		}
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(requestedPassword);
//		userLookup.setPassword(encodedPassword);
//		UserV2 savedUser = userRepository.save(userLookup);
//		super.saveUserHistory(savedUser.getEditedBy(), "User", action, savedUser.getName());
//		return savedUser;
//	}

}
