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
    // Service responsible for handling product orders' operations
    private ProductOrderService productOrderService;

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get a products for a specific order")
    // Get all products associated with a specific order.
    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getProductsOfOrder(@PathVariable Integer customerId,@PathVariable Integer orderId) {
        // Return the list of products associated with a specific order.
        return ResponseEntity.ok(productOrderService.getProductsOfOrder( customerId,orderId));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get a specific product for a specific order")
    // Get a specific product from a specific order.
    @GetMapping("/{productId}")
    public ResponseEntity<ProductOrderDTO> getProductOfOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @PathVariable Integer productId ) {
        // Return the specified product from a specific order.
        return ResponseEntity.ok(productOrderService.getProductOfOrder(customerId,orderId, productId));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "create a specific product to a specific order")
    // create a specific product to a specific order.
    @PostMapping
    public ResponseEntity<ProductOrderDTO> postProductToOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @RequestBody ProductOrderDTO productOrderDTO) {
        // Return the newly created product in a specific order.
        return ResponseEntity.ok(productOrderService.addProductToOrder(customerId,orderId, productOrderDTO));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Update a specific product for a specific order")
    // Update a specific product of a specific order
    @PutMapping("/{productId}")
    public ResponseEntity<ProductOrderDTO> updateProductOfOrder( @PathVariable Integer customerId , @PathVariable Integer orderId, @PathVariable Integer productId, @RequestBody ProductOrderDTO productOrderDTO) {
        // Return the updated product from a specific order.
        return ResponseEntity.ok(productOrderService.updateProductOfOrder(customerId,orderId, productId, productOrderDTO));
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Delete a specific product for a specific order")
    // Delete a specific product of a specific order
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductOfOrder( @PathVariable Integer customerId ,@PathVariable Integer orderId, @PathVariable Integer productId) {
        productOrderService.deleteProductOfOrder(customerId,orderId, productId);
        // Return a success response.
        return ResponseEntity.ok().build();
    }

}