package com.internal.service.template.repository;
import com.internal.service.template.model.Orderitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Long>{

}
