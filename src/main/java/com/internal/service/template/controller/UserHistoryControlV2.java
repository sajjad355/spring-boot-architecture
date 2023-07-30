package com.internal.service.template.controller;

import com.internal.service.template.model.UserHistoryV2;
import com.internal.service.template.repository.UserHistoryRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/userhistoryv2")
public class UserHistoryControlV2 extends ParentControl {

  @Autowired
  UserHistoryRepositoryV2 userHistoryRepo;

  private final static Logger LOGGER = LoggerFactory.getLogger(UserHistoryControlV2.class);

  /**
   * Get a paginated subset of all user histories between now and a given number
   * of days ago
   *
   * @param days A number of days to traverse
   * @param page A requested page of user histories
   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/all/{days}/{page}")
  public List<UserHistoryV2> getAllUserHistoriesByPage(@PathVariable(value = "days") int days,
                                                 @PathVariable(value = "page") int page) {
    PageRequest pageable = PageRequest.of(((page <= 1) ? 0 : page - 1), 10, Sort.by("id").descending());
    LOGGER.info("getAllUserHistoriesByPage");
    // return userHistoryRepo.loadAllUserHistory(days, new PageRequest(((page <= 1)
    // ? 0 : page -1), 10));
    return userHistoryRepo.loadAllUserHistoryByPage(days, pageable);

  }

  /**
   * Get a paginated subset of all user histories between now and a given number
   * of days ago
   *
   * @param days A number of days to traverse
   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/all/{days}")
  public List<UserHistoryV2> getAllUserHistoriesByDays(@PathVariable(value = "days") int days) {
    LOGGER.info("getAllUserHistoriesByDays");
    return userHistoryRepo.loadAllUserHistoryByDays(days);
  }


  /**
   * Get all check in/out audit trails for a given vial ID
   *
   * @param vialId An ID for a particular inventory vial

   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/allVialHistory/{vialId}")
  public List<UserHistoryV2> getAllVialHistory(@PathVariable(value = "vialId") int vialId) {

    LOGGER.info("allVialHistory");

    return userHistoryRepo.loadAllVialHistory(vialId);

  }


  /**
   * Get a paginated subset of check in/out audit trails for a given vial ID
   *
   * @param vialId An ID for a particular inventory vial
   * @param days A number of days to traverse
   * @param page A requested page of user histories
   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/allVialHistoryByPage/{vialId}/{days}/{page}")
  public List<UserHistoryV2> getAllVialHistoryByPage(@PathVariable(value = "vialId") int vialId,
                                                     @PathVariable(value = "days") int days,
                                                     @PathVariable(value = "page") int page) {

    LOGGER.info("allVialHistoryByPage");
    PageRequest pageable = PageRequest.of(((page <= 1) ? 0 : page - 1), 10, Sort.by("id").descending());

    return userHistoryRepo.loadAllVialHistoryByPage(vialId, days, pageable);

  }

  /**
   * Get a paginated subset of check in/out audit trails for a given vial ID
   *
   * @param vialId An ID for a particular inventory vial
   * @param days A number of days to traverse
   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/allVialHistoryByDays/{vialId}/{days}")
  public List<UserHistoryV2> getAllVialHistoryByDays(@PathVariable(value = "vialId") int vialId,
                                                     @PathVariable(value = "days") int days) {

    LOGGER.info("allVialHistoryByDays");

    return userHistoryRepo.loadAllVialHistoryByDays(vialId, days);

  }

  /**
   * Get a paginated subset of a given user's history between now and a given
   * number of days ago
   *
   * @param user A user
   * @param days A number of days to traverse
   * @param page A requested page of user histories
   * @return A list of UserHistoryV2 instances
   */
  @GetMapping("/{user}/{days}/{page}")
  public List<UserHistoryV2> getSpecificUserHistory(@PathVariable(value = "user") String user,
                                                    @PathVariable(value = "days") int days, @PathVariable(value = "page") int page) {
    PageRequest pageable = PageRequest.of(((page <= 1) ? 0 : page - 1), 10, Sort.by("id").descending());
    LOGGER.info("getSpecificUserHistory");
    return userHistoryRepo.loadSpecificUserHistory(user, days, pageable);
  }

  @PostMapping("/auditTrail")
  public UserHistoryV2 addTimeAudit(@RequestBody UserHistoryV2 userHistory) {
    LOGGER.info(String.format("addTimeAudit received request details: %s", userHistory));

    UserHistoryV2 userhistoryResponse;

    userhistoryResponse=super.saveUserHistory(userHistory.getUsername(), userHistory.getComponent(), userHistory.getActivity(),
      userHistory.getDescription());

    return userhistoryResponse;
  }



}
