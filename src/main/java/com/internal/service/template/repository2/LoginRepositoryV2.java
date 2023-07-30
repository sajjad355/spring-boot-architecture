package com.internal.service.template.repository2;

import com.internal.service.template.model2.UserV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepositoryV2 extends JpaRepository<UserV2, Long> {

  @Query(value="SELECT DISTINCT user FROM UserV2 user WHERE user.userName LIKE :userName")
  UserV2 searchSingleUserByUserName(@Param(value = "userName") String userName);

UserV2 findByUserId(Long userID);
}
