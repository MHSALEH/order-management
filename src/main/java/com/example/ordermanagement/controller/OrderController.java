package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.OrderDTO;
import com.example.ordermanagement.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Api(tags = "Orders")
@RestController
@RequestMapping("/api/v1/customers/{customerId}/orders")
public class OrderController {
    @Autowired
    // use OrderService to manage orders.
    private OrderService orderService;
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get all Orders by a specific Customer")
    // method to retrieves all orders made by a specific customer.
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCustomer(@PathVariable Integer customerId) {
        // Add a link to each order's products (hateoas)
        List<OrderDTO> orderDTOS = orderService.getAllOrdersByCustomer(customerId);
            for ( OrderDTO order: orderDTOS ) {
                Link ordersLink = linkTo(methodOn(ProductOrderController.class)
                        .getProductsOfOrder(order.getCustomerId() , order.getId()))
                        .withRel("productOrders");
                order.add(ordersLink);
            }
        // Return the list of orders with links to their products.
            return ResponseEntity.ok(orderDTOS);

    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get  Order by a specific Id and a specific Customer")
    // This method retrieves a specific order made by a specific customer.
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderByIdAndCustomer(@PathVariable Integer id, @PathVariable Integer customerId) {
        OrderDTO orderDTO = orderService.getOrderByIdAndCustomer(id, customerId);
        // Add a link to this order's products (hateoas).
        Link ordersLink = linkTo(methodOn(ProductOrderController.class)
                .getProductsOfOrder(orderDTO.getCustomerId() , orderDTO.getId()))
                .withRel("productOrders");
        orderDTO.add(ordersLink);
        // Return the order with a link to its products.
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "create Order for and a specific Customer")
    // This method creates a new order for a specific customer.
    @PostMapping
    public ResponseEntity<OrderDTO> createOrderForCustomer(@PathVariable Integer customerId, @RequestBody OrderDTO orderDTO) {
        OrderDTO newOrderDTO = orderService.createOrderForCustomer(customerId, orderDTO);
        // Return the new created order.
        return new ResponseEntity<>(newOrderDTO, HttpStatus.CREATED);
    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "update a specific Order by Order Id and a specific Customer")
    // This method updates a specific order made by a specific customer.
    @PutMapping("{id}")
    public ResponseEntity<OrderDTO> updateOrderOfCustomer(@PathVariable Integer id, @PathVariable Integer customerId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrderDTO = orderService.updateOrderOfCustomer(id, customerId, orderDTO);
        // Return the updated order.
        return new ResponseEntity<>(updatedOrderDTO, HttpStatus.OK);
    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Delete a specific Order by Order Id and a specific Customer")
    // This method deletes a specific order made by a specific customer.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderOfCustomer(@PathVariable Integer id, @PathVariable Integer customerId) {
        orderService.deleteOrderOfCustomer(id, customerId);
        // Return an empty response to indicate success.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}