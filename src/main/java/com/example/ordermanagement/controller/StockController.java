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
    // Service responsible for handling product orders' operations.
    private StockService stockService;

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get all stocks for a specific product")
    // Get all products associated with a specific order.
    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocksByProductId(@PathVariable Integer productId) {
        // Return the list of products associated with a specific order.
        return ResponseEntity.ok(stockService.getAllStocksByProductId(productId));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get specific stock by stock ID for a specific product")
    // Get a specific product from a specific order.
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id) {
        // Return the specified product from a specific order.
        return ResponseEntity.ok(stockService.getStockByProductIdAndStockId(productId, id));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Create a new stock entry for a specific product")
    // create a specific product to a specific order.
    @PostMapping
    public ResponseEntity<StockDTO> createStockByProductId(@PathVariable Integer productId, @RequestBody StockDTO stockDTO) {
        // Return the newly created product in a specific order.
        return ResponseEntity.ok(stockService.createStockByProductId(productId, stockDTO));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Update stock for a specific product")
    // updates a specific product from a specific order.
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id, @RequestBody StockDTO stockDTO) {
        // Return the updated product from a specific order.
        return ResponseEntity.ok(stockService.updateStockByProductIdAndStockId(productId, id, stockDTO));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Delete stock by stock ID for a specific product")
    // deletes a specific product from a specific order.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockByProductIdAndStockId(@PathVariable Integer productId, @PathVariable Integer id) {
        stockService.deleteStockByProductIdAndStockId(productId, id);
        // Return a success response.
        return ResponseEntity.ok().build();
    }
}