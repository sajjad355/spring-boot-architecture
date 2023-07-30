package com.internal.service.template.controller2;

import com.internal.service.template.dto.AuthInput;
import com.internal.service.template.dto.AuthOutput;
import com.internal.service.template.dto.TemplateUserDetailsService;
import com.internal.service.template.model2.UserV2;
import com.internal.service.template.repository2.LoginRepositoryV2;
import com.internal.service.template.utils.JWTUtil;
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
@RequestMapping("/loginV2")
public class LoginControlV2 extends ParentControl {

  private final static Logger LOGGER = LoggerFactory.getLogger(LoginControlV2.class);

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  TemplateUserDetailsService userDetailService;

  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  LoginRepositoryV2 loginRepository;


  @PostMapping("/authenticate")
  AuthOutput authenticate(@RequestBody AuthInput data) throws AuthenticationException {
    String token = "";
    AuthOutput output = null;
    try {
      UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
      Authentication authentication = authManager.authenticate(authReq);
      UserV2 user = loginRepository.searchSingleUserByUserName(data.getUsername());
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      token = jwtUtil.generateToken(userDetails.getUsername());
      output = new AuthOutput(user, token);
      super.saveUserHistory(user.getName(), "Login", "User authentication", "User logged on");
    } catch (Exception ex) {
      throw ex;
    }
    return output;
  }
}
