package com.internal.service.template.repository;

import com.internal.service.template.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

  @Query(value="SELECT DISTINCT user FROM User user WHERE user.userName LIKE :userName")
  User searchSingleUserByUserName(@Param(value = "userName") String userName);

User findByUserId(Long userID);
}
