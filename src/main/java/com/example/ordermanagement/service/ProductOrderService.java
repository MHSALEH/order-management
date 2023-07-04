package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.ProductOrderDTO;

import java.util.List;

public interface ProductOrderService {

//    void deleteOrderOfProduct(Integer customerId, Integer productId, Integer orderId);

    List<ProductOrderDTO> getProductsOfOrder(Integer customerId, Integer orderId);
    ProductOrderDTO getProductOfOrder(Integer customerId, Integer orderId, Integer productId);
    ProductOrderDTO addProductToOrder(Integer customerId, Integer orderId, ProductOrderDTO productOrderDTO);
    ProductOrderDTO updateProductOfOrder(Integer customerId, Integer orderId, Integer productId, ProductOrderDTO productOrderDTO);
    void deleteProductOfOrder(Integer customerId, Integer orderId, Integer productId);
//    List<ProductOrderDTO> getOrdersOfProduct(Integer customerId, Integer productId);
//    ProductOrderDTO getOrderOfProduct(Integer customerId, Integer productId, Integer orderId);
//    ProductOrderDTO addOrderToProduct(Integer customerId, Integer productId, ProductOrderDTO productOrderDTO);
//    ProductOrderDTO updateOrderOfProduct(Integer customerId, Integer productId, Integer orderId, ProductOrderDTO productOrderDTO);





}

