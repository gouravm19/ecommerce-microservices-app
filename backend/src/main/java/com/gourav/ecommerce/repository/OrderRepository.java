package com.gourav.ecommerce.repository;

import com.gourav.ecommerce.model.OrderEntity;
import com.gourav.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser(User user);
}
