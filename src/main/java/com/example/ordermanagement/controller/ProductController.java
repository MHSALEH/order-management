package com.example.ordermanagement.controller;
import com.example.ordermanagement.dto.ProductDTO;
import com.example.ordermanagement.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Api(tags = "Products")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    // Instantiation of ProductService to manage product operations.
    private final ProductService productService;
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get All Products , user-Admin")
    // This method retrieves all products.
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        // Add a link to each product's stocks (hateoas).
        List<ProductDTO> productDTOS=productService.getAllProducts();
        for ( ProductDTO product: productDTOS ) {
            Link stocksLink = linkTo(methodOn(StockController.class)
                    .getAllStocksByProductId(product.getId()))
                    .withRel("Stocks ");
            product.add(stocksLink);
        }
        // Return the list of products with links to their stocks.
        return ResponseEntity.ok(productDTOS);

    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Get a specific Product by Id ,user-Admin")
    // This method retrieves a specific product by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO != null) {
            // Add a link to this product's stocks (hateoas).
            Link stockLink = linkTo(methodOn(StockController.class)
                    .getAllStocksByProductId(productDTO.getId()))
                    .withRel("Stocks ");
            productDTO.add(stockLink);
            // Return the product with a link to its stocks.
            return ResponseEntity.ok(productDTO);
        } else {
            // If the product is not found, return an error.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Post a specific Product by Id ,user Admin")
    // This method creates a new product.
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        // Return the newly created product.
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Update a specific Product by Id ,user Admin")
    // This method updates a specific product by ID.
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        if (updatedProductDTO != null) {
            // Return the updated product.
            return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
        } else {
            // If the product is not found, return an error.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // annotation describes a function for use in the API documentation (Swagger)
    @ApiOperation(value = "Delete a specific Product by Id ,user Admin")
    // This method deletes a specific product by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        // Return a success response.
        return ResponseEntity.ok().build();
    }


}