package com.internal.service.template.repository;

import com.internal.service.template.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Set<User> findAllByUserIdIn(Set<Long> userIds);

    @Query(value = "SELECT user FROM User user ORDER BY user.userId DESC")
    List<User> getAllUsers();

    @Query(value = "SELECT user FROM User user " + "WHERE user.name LIKE %:key% " + "OR user.email LIKE %:key% "
            + "ORDER BY user.userId DESC")
    List<User> searchByNameOrEmail(@Param(value = "key") String key);

    public List<User> findAllByUserIdIn(long[] userId);

    Optional<User> findByEmail(String email);

    User findByUserId(long userId);

    Optional<User> findByUserName(String username);
}
