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
    private OrderService orderService;
    @ApiOperation(value = "Get all Orders by a specific Customer")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCustomer(@PathVariable Integer customerId) {
        List<OrderDTO> orderDTOS = orderService.getAllOrdersByCustomer(customerId);

            for ( OrderDTO order: orderDTOS ) {
                Link ordersLink = linkTo(methodOn(ProductOrderController.class)
                        .getProductsOfOrder(order.getCustomerId() , order.getId()))
                        .withRel("productOrders");
                order.add(ordersLink);
            }
            return ResponseEntity.ok(orderDTOS);

    }
    @ApiOperation(value = "Get  Order by a specific Id and a specific Customer")
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderByIdAndCustomer(@PathVariable Integer id, @PathVariable Integer customerId) {
        OrderDTO orderDTO = orderService.getOrderByIdAndCustomer(id, customerId);

        Link ordersLink = linkTo(methodOn(ProductOrderController.class)
                .getProductsOfOrder(orderDTO.getCustomerId() , orderDTO.getId()))
                .withRel("productOrders");
        orderDTO.add(ordersLink);

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "create Order for and a specific Customer")
    @PostMapping
    public ResponseEntity<OrderDTO> createOrderForCustomer(@PathVariable Integer customerId, @RequestBody OrderDTO orderDTO) {
        OrderDTO newOrderDTO = orderService.createOrderForCustomer(customerId, orderDTO);
        return new ResponseEntity<>(newOrderDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update a specific Order by Order Id and a specific Customer")
    @PutMapping("{id}")
    public ResponseEntity<OrderDTO> updateOrderOfCustomer(@PathVariable Integer id, @PathVariable Integer customerId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrderDTO = orderService.updateOrderOfCustomer(id, customerId, orderDTO);
        return new ResponseEntity<>(updatedOrderDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a specific Order by Order Id and a specific Customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderOfCustomer(@PathVariable Integer id, @PathVariable Integer customerId) {
        orderService.deleteOrderOfCustomer(id, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}