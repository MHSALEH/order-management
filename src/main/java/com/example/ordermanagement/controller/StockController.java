package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.StockDTO;
import com.example.ordermanagement.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Stock")
@RestController
@RequestMapping("/api/v1/products/{productId}/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "Get all stocks for a specific product")
    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocksByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok(stockService.getAllStocksByProductId(productId));
    }

    @ApiOperation(value = "Get specific stock by stock ID for a specific product")
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id) {
        return ResponseEntity.ok(stockService.getStockByProductIdAndStockId(productId, id));
    }

    @ApiOperation(value = "Create a new stock entry for a specific product")
    @PostMapping
    public ResponseEntity<StockDTO> createStockByProductId(@PathVariable Integer productId, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.createStockByProductId(productId, stockDTO));
    }

    @ApiOperation(value = "Update stock for a specific product")
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStockByProductIdAndStockId(productId, id, stockDTO));
    }

    @ApiOperation(value = "Delete stock by stock ID for a specific product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id) {
        stockService.deleteStockByProductIdAndStockId(productId, id);
        return ResponseEntity.ok().build();
    }
}