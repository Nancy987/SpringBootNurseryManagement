package com.Prograd.Springboot.Backend.Repository;

import com.Prograd.Springboot.Backend.Modals.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select u FROM Order u WHERE u.customer_id =:id")
    public List<Order> findByCustomerId(@Param("id") int id);
}
