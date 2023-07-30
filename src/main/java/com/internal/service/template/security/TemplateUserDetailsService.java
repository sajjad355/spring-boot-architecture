package com.internal.service.template.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.internal.service.template.model.UserV2;
import com.internal.service.template.repository.UserRepositoryV2;

@Service
public class TemplateUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepositoryV2 userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//Email is used as user name
		Optional<UserV2> user = userRepo.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found username: " + username));

		return user.map(TemplateUserDetails::new).get();
	}

}
