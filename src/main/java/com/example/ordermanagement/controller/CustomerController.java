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
  // creates an instance of the CustomerService class
    private CustomerService customerService;
  // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get all customers")
  // annotation handles HTTP GET requests.
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        // Getting all customers
        List<CustomerDTO> cs=customerService.getAllCustomers();
        // Adding a link to each customer's orders (hateoas)
        for ( CustomerDTO c: cs ) {
            Link ordersLink = linkTo(methodOn(OrderController.class)
                    .getAllOrdersByCustomer(c.getId()))
                    .withRel("customerOrders");
            c.add(ordersLink);
        }
        // Returning the list of customers with links to their orders
        return ResponseEntity.ok(cs);
    }

    // Describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get customer by specific ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id, Authentication auth) {
            // Getting customer by specific ID
            CustomerDTO customerDTO = customerService.getCustomerById(id);
            // Adding a link to customer's orders (hateoas)
            Link ordersLink = linkTo(methodOn(OrderController.class)
                    .getAllOrdersByCustomer(customerDTO.getId()))
                    .withRel("customerOrders");
            customerDTO.add(ordersLink);
            // Returning the customer with link to their orders
            return ResponseEntity.ok(customerDTO);
    }
    // Describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Create new customer")
    // Handles HTTP POST requests.
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        // Creating a new customer
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }
    // Describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Update an customer")
    // Handles HTTP PUT requests.
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        // Updating an existing customer
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }
    // Describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Delete customer by specific ID")
    // Handles HTTP DELETE requests.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        // Deleting a customer
        customerService.deleteCustomer(id);
        // Returning an empty response
        return ResponseEntity.ok().build();
    }
}