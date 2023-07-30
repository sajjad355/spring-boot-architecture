package com.internal.service.template.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internal.service.template.model.UserHistory;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
