package com.internal.service.template.controller;

import com.internal.service.template.model.UserV2;
import com.internal.service.template.repository.UserRepositoryV2;
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
@RequestMapping("/userV2")
public class UserControlV2 extends ParentControl {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserControlV2.class);

	@Autowired
	UserRepositoryV2 userRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/all")
	public List<UserV2> getAllUsers() {
		LOGGER.info("/userV2 received GET /all mapped to getAllUsers");
		List<UserV2> allUsers = userRepository.findAll();
		return allUsers;
	}

	@PostMapping("/save")
	public UserV2 save(@Valid @RequestBody UserV2 userData) throws MessagingException {
		LOGGER.info(String.format("/save received request with user %s", userData));
		String userPassword = null;
		String action;
		String auditTrailRecord;
		if (userData.getUserId() > 0) {
			UserV2 user = userRepository.findByUserId(userData.getUserId());
			if (user == null) {
				throw new EntityNotFoundException(UserV2.class, "id", String.valueOf(user.getUserId()));
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

		UserV2 savedUser = userRepository.save(userData);
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
