package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.CustomerDTO;
import com.example.ordermanagement.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "Customers")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Get all customers")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> cs=customerService.getAllCustomers();
        for ( CustomerDTO c: cs ) {
            Link ordersLink = linkTo(methodOn(OrderController.class)
                    .getAllOrdersByCustomer(c.getId()))
                    .withRel("customerOrders");
            c.add(ordersLink);
        }
        return ResponseEntity.ok(cs);
    }

    @ApiOperation(value = "Get customer by specific ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id, Authentication auth) {

            CustomerDTO customerDTO = customerService.getCustomerById(id);
            Link ordersLink = linkTo(methodOn(OrderController.class)
                    .getAllOrdersByCustomer(customerDTO.getId()))
                    .withRel("customerOrders");
            customerDTO.add(ordersLink);
            return ResponseEntity.ok(customerDTO);
    }

    @ApiOperation(value = "Create new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @ApiOperation(value = "Update an customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }

    @ApiOperation(value = "Delete customer by specific ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}