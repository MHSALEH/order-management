package com.example.ordermanagement.repository;


import com.example.ordermanagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProduct_Id(Integer productId);
    Optional<Stock> findByIdAndProduct_Id(Integer stockId, Integer productId);
    void deleteByIdAndProduct_Id(Integer stockId, Integer productId);
}