package com.internal.service.template.repository2;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internal.service.template.model2.UserHistoryV2;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserHistoryRepositoryV2 extends JpaRepository<UserHistoryV2, Long> {

    @Query(value =
      "Select userhistory " +
      "From UserHistoryV2 userhistory " +
      "Where Date(userhistory.activityTime) " +
      "BETWEEN CURDATE()- :days  AND  CURDATE() " +
      "ORDER BY userhistory.userHistoryId DESC")
    public List<UserHistoryV2> loadAllUserHistoryByPage(@Param("days") int days, Pageable pageable);

  @Query(value =
    "Select userhistory " +
      "From UserHistoryV2 userhistory " +
      "Where Date(userhistory.activityTime) " +
      "BETWEEN CURDATE()- :days  AND  CURDATE() " +
      "ORDER BY userhistory.userHistoryId DESC")
  public List<UserHistoryV2> loadAllUserHistoryByDays(@Param("days") int days);

    @Query(value =
        "Select userhistory " +
        "From UserHistoryV2 userhistory " +
        "Where userhistory.vialId = :vialId " +
          "ORDER BY userhistory.userHistoryId DESC")
    public List<UserHistoryV2> loadAllVialHistory(@Param("vialId") int vialId);

    @Query(value =
        "Select userhistory " +
        "From UserHistoryV2 userhistory Where " +
        "Date(userhistory.activityTime) >= CURDATE()- :days  AND " +
          "Date(userhistory.activityTime) <= CURDATE() AND " +
          "userhistory.vialId = :vialId " +
        "ORDER BY userhistory.userHistoryId DESC")
    public List<UserHistoryV2> loadAllVialHistoryByPage(@Param("vialId") int vialId, @Param("days") int days, Pageable pageable);

  @Query(value =
    "Select userhistory " +
      "From UserHistoryV2 userhistory Where " +
      "Date(userhistory.activityTime) >= CURDATE()- :days  AND " +
      "Date(userhistory.activityTime) <= CURDATE() AND " +
      "userhistory.vialId = :vialId " +
      "ORDER BY userhistory.userHistoryId DESC")
  public List<UserHistoryV2> loadAllVialHistoryByDays(@Param("vialId") int vialId, @Param("days") int days);

    @Query(value =
      "Select userhistory " +
      "From UserHistoryV2 userhistory " +
      "Where userhistory.username " +
      "LIKE %:user% and Date(userhistory.activityTime) " +
      "BETWEEN CURDATE()- :days  AND  CURDATE() " +
      "ORDER BY userhistory.userHistoryId DESC ")
    public List<UserHistoryV2> loadSpecificUserHistory(
            @Param("user") String user, @Param("days") int days, Pageable pageable);
}
