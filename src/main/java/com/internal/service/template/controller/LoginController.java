package com.internal.service.template.controller;


import com.internal.service.template.model.User;
import com.internal.service.template.repository.LoginRepository;
import com.internal.service.template.request.AuthInput;
import com.internal.service.template.response.AuthOutput;
import com.internal.service.template.security.JWTUtil;
import com.internal.service.template.security.TemplateUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/login")
public class LoginController extends ParentController {

  private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  TemplateUserDetailsService userDetailService;

  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  LoginRepository loginRepository;


  @PostMapping("/authenticate")
  AuthOutput authenticate(@RequestBody AuthInput data) throws AuthenticationException {
    String token = "";
    AuthOutput output = null;
    try {
      UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
      Authentication authentication = authManager.authenticate(authReq);
      User user = loginRepository.searchSingleUserByUserName(data.getUsername());
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      token = jwtUtil.generateToken(userDetails.getUsername());
      output = new AuthOutput(user, token);
//      super.saveUserHistory(user.getName(), "Login", "User authentication", "User logged on");
    } catch (Exception ex) {
      throw ex;
    }
    return output;
  }
}
