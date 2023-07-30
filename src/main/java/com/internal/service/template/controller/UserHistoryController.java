package com.internal.service.template.controller;

import com.internal.service.template.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/userhistory")
public class UserHistoryController extends ParentController {

  @Autowired
  UserHistoryRepository userHistoryRepo;

  private final static Logger LOGGER = LoggerFactory.getLogger(UserHistoryController.class);

}
