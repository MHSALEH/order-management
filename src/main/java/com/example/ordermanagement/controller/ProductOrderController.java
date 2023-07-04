package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.ProductOrderDTO;
import com.example.ordermanagement.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "Product Orders")
@RestController
@RequestMapping("/api/v1/customers/{customerId}/orders/{orderId}/products")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;


    // Get the products of a specific order
    @ApiOperation(value = "Get a products for a specific order")
    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getProductsOfOrder(@PathVariable Integer customerId,@PathVariable Integer orderId) {
        return ResponseEntity.ok(productOrderService.getProductsOfOrder( customerId,orderId));
    }

    // Get a specific product of a specific order
    @ApiOperation(value = "Get a specific product for a specific order")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductOrderDTO> getProductOfOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @PathVariable Integer productId ) {
        return ResponseEntity.ok(productOrderService.getProductOfOrder(customerId,orderId, productId));
    }


    // Post a specific product to a specific order
    @ApiOperation(value = "create a specific product to a specific order")
    @PostMapping
    public ResponseEntity<ProductOrderDTO> postProductToOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @RequestBody ProductOrderDTO productOrderDTO) {
        return ResponseEntity.ok(productOrderService.addProductToOrder(customerId,orderId, productOrderDTO));
    }

    // Update a specific product of a specific order
    @ApiOperation(value = "Update a specific product for a specific order")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductOrderDTO> updateProductOfOrder( @PathVariable Integer customerId , @PathVariable Integer orderId, @PathVariable Integer productId, @RequestBody ProductOrderDTO productOrderDTO) {
        return ResponseEntity.ok(productOrderService.updateProductOfOrder(customerId,orderId, productId, productOrderDTO));
    }

    // Delete a specific product of a specific order
    @ApiOperation(value = "Delete a specific product for a specific order")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductOfOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @PathVariable Integer productId) {
        productOrderService.deleteProductOfOrder(customerId,orderId, productId);
        return ResponseEntity.ok().build();
    }

}