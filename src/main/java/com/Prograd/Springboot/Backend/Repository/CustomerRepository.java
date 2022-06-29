package com.Prograd.Springboot.Backend.Repository;

import com.Prograd.Springboot.Backend.Modals.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByUsername(String username);

    @Query("select u FROM Customer u WHERE u.role =:r")
    public List<Customer> findByRole(@Param("r") String role);
}
