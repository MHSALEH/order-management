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
    private final ProductService productService;

    @ApiOperation(value = "Get All Products , user-Admin")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> productDTOS=productService.getAllProducts();

        for ( ProductDTO product: productDTOS ) {
            Link stocksLink = linkTo(methodOn(StockController.class)
                    .getAllStocksByProductId(product.getId()))
                    .withRel("Stocks ");
            product.add(stocksLink);
        }
        return ResponseEntity.ok(productDTOS);

    }


    @ApiOperation(value = "Get a specific Product by Id ,user-Admin")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO != null) {
            Link stockLink = linkTo(methodOn(StockController.class)
                    .getAllStocksByProductId(productDTO.getId()))
                    .withRel("Stocks ");
            productDTO.add(stockLink);
            return ResponseEntity.ok(productDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "Post a specific Product by Id ,user Admin")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a specific Product by Id ,user Admin")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProductDTO = productService.updateProduct(id, productDTO);
        if (updatedProductDTO != null) {
            return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "Delete a specific Product by Id ,user Admin")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}