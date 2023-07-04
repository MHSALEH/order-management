package com.example.ordermanagement.repository;

import com.example.ordermanagement.entity.Customer;
import com.example.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);


    @Query("SELECT o FROM Order o WHERE o.customer = :customer AND o.id = :id")
    Optional<Order> findByCustomerAndOrderId(@Param("customer") Customer customer, @Param("id") Integer id);
}