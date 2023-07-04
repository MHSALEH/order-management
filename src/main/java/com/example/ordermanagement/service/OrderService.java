package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.OrderDTO;

import java.util.List;



public interface OrderService {

    List<OrderDTO> getAllOrdersByCustomer(Integer customerId);
    OrderDTO getOrderByIdAndCustomer(Integer id, Integer customerId);
    OrderDTO createOrderForCustomer(Integer customerId, OrderDTO orderDTO);
    OrderDTO updateOrderOfCustomer(Integer id, Integer customerId, OrderDTO orderDTO);
    void deleteOrderOfCustomer(Integer id, Integer customerId);



}