package com.example.ordermanagement.service.impl;


import com.example.ordermanagement.dto.ProductDTO;
import com.example.ordermanagement.entity.Product;
import com.example.ordermanagement.exception.ResourceNotFoundException;
import com.example.ordermanagement.repository.ProductRepository;
import com.example.ordermanagement.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;



    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "id" , id));
        return convertToDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        return convertToDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {

        // Fetch the product by id
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" , "id" , id));

        // Set the new values
        product.setSlug(productDTO.getSlug());
        product.setName(productDTO.getName());
        product.setReference(productDTO.getReference());
        product.setPrice(productDTO.getPrice());
        product.setVat(productDTO.getVat());
        product.setStockable(productDTO.getStockable());
        // Save the updated product and convert it to ProductDTO
        ProductDTO updatedProductDTO = convertToDTO(productRepository.save(product));

        return updatedProductDTO;
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    //convert Product to ProductDTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setSlug(product.getSlug());
        productDTO.setName(product.getName());
        productDTO.setReference(product.getReference());
        productDTO.setPrice(product.getPrice());
        productDTO.setVat(product.getVat());
        productDTO.setStockable(product.getStockable());
        return productDTO;
    }

    // convert ProductDTO to Product
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setSlug(productDTO.getSlug());
        product.setName(productDTO.getName());
        product.setReference(productDTO.getReference());
        product.setPrice(productDTO.getPrice());
        product.setVat(productDTO.getVat());
        product.setStockable(productDTO.getStockable());
        return product;
    }
}