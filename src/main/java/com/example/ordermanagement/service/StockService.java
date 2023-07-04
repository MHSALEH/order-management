package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getAllStocks();
    List<StockDTO> getAllStocksByProductId(Integer productId);
    StockDTO getStockById(Integer id);
    StockDTO getStockByProductIdAndStockId(Integer productId, Integer stockId);
    StockDTO createStock(StockDTO stockDTO);
    StockDTO createStockByProductId(Integer productId, StockDTO stockDTO);
    StockDTO updateStock(Integer id, StockDTO stockDTO);
    StockDTO updateStockByProductIdAndStockId(Integer productId, Integer stockId, StockDTO stockDTO);
    void deleteStock(Integer id);
    void deleteStockByProductIdAndStockId(Integer productId, Integer stockId);
}