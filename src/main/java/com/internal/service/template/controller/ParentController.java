package com.internal.service.template.controller;

import com.internal.service.template.repository.UserHistoryRepository;
import com.internal.service.template.model.UserHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ParentController {

  @Autowired
  UserHistoryRepository userHistoryRepo;

  private final static Logger LOGGER = LoggerFactory.getLogger(ParentController.class);


  @Transactional(rollbackFor = Exception.class)
  protected UserHistory saveUserHistory(String username, String component, String activity, String description) {
    LOGGER.info("saveUserHistory");
    UserHistory userHistory = new UserHistory();

    userHistory.setActivity(activity);
    userHistory.setActivityTime(new java.util.Date(System.currentTimeMillis()));
    userHistory.setComponent(component);
    StringBuilder sb = new StringBuilder(description);
    sb.append(String.format(" Activity Timestamp: %s", getCurrentFormattedTimeStamp()));
    userHistory.setDescription(sb.toString());
    userHistory.setUsername(username);
    LOGGER.info(String.format("UserHistory Details : %s", userHistory));
    UserHistory userHistoryResponse = userHistoryRepo.save(userHistory);
    LOGGER.info("User History save complete");
    return userHistoryResponse;
  }

  private String getCurrentFormattedTimeStamp() {
    java.util.Date now = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    return sdf.format(now);
  }


}
