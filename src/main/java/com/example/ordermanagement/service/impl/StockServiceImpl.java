package com.example.ordermanagement.service.impl;


import com.example.ordermanagement.dto.StockDTO;
import com.example.ordermanagement.entity.Product;
import com.example.ordermanagement.entity.Stock;
import com.example.ordermanagement.exception.ResourceNotFoundException;
import com.example.ordermanagement.repository.ProductRepository;
import com.example.ordermanagement.repository.StockRepository;
import com.example.ordermanagement.service.StockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> getAllStocksByProductId(Integer productId) {
        return stockRepository.findByProduct_Id(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockDTO getStockById(Integer id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock" , "id" , id));
        return convertToDTO(stock);
    }

    @Override
    public StockDTO getStockByProductIdAndStockId(Integer productId, Integer stockId) {
        Stock stock = stockRepository.findByIdAndProduct_Id(stockId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for the given product and stock ID"  , "productId: "+productId  + " stockId:" +stockId , stockId));
        return convertToDTO(stock);
    }

    @Override
    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        return convertToDTO(stockRepository.save(stock));
    }

    @Override
    public StockDTO createStockByProductId(Integer productId, StockDTO stockDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "productId: "+productId  , productId ));
        Stock stock = convertToEntity(stockDTO);
        stock.setProduct(product);
        return convertToDTO(stockRepository.save(stock));
    }

    @Override
    public StockDTO updateStock(Integer id, StockDTO stockDTO) {
        stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id"  , id ));
        Stock stock = convertToEntity(stockDTO);
        stock.setId(id);
        return convertToDTO(stockRepository.save(stock));
    }

    @Override
    public StockDTO updateStockByProductIdAndStockId(Integer productId, Integer stockId, StockDTO stockDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found","productId"  , productId ));
        stockRepository.findByIdAndProduct_Id(stockId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for the given product and stock ID"  , "productId: "+productId  + " stockId:" +stockId , stockId));

        Stock stock = convertToEntity(stockDTO);
        stock.setId(stockId);
        stock.setProduct(product);
        return convertToDTO(stockRepository.save(stock));
    }

    @Override
    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteStockByProductIdAndStockId(Integer productId, Integer stockId) {
        stockRepository.deleteByIdAndProduct_Id(stockId, productId);
    }

    private StockDTO convertToDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setQuantity(stock.getQuantity());
        stockDTO.setUpdateAt(stock.getUpdateAt());
        if (stock.getProduct() != null) {
            stockDTO.setProductId(stock.getProduct().getId());
        }
        return stockDTO;
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setQuantity(stockDTO.getQuantity());
        stock.setUpdateAt(stockDTO.getUpdateAt());
        if (stockDTO.getProductId() != null) {
            Product product = productRepository.findById(stockDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found","productId"  , stockDTO.getProductId() ));
            stock.setProduct(product);
        }
        return stock;
    }
}