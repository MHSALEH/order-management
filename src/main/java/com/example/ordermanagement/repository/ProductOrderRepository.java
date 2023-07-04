package com.example.ordermanagement.repository;

import com.example.ordermanagement.entity.Order;
import com.example.ordermanagement.entity.ProductOrder;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {


    List<ProductOrder> findByOrderId(Integer orderId);

    // Find all product orders by product ID
    List<ProductOrder> findByProductId(Integer productId);

    // Find a product order by order ID and product ID
    ProductOrder findByOrderIdAndProductId(Integer orderId, Integer productId);

    @Query("SELECT po FROM ProductOrder po WHERE po.order.customer.id = :customerId AND po.order.id = :orderId AND po.product.id = :productId")
    ProductOrder findByOrder_Customer_IdAndOrder_IdAndProduct_Id(@Param("customerId") Integer customerId, @Param("orderId") Integer orderId, @Param("productId") Integer productId);
    //ProductOrder findByOrder_Customer_IdAndOrder_IdAndProduct_Id(Integer customerId, Integer orderId, Integer productId);
    @Query("SELECT po FROM ProductOrder po WHERE po.order.customer.id = :customerId AND po.order.id = :orderId AND po.product.id = :productId")
    Optional<ProductOrder> findFirstByCustomerIdAndOrderIdAndProductId(@Param("customerId") Integer customerId, @Param("orderId") Integer orderId, @Param("productId") Integer productId);

    @Query("SELECT o FROM ProductOrder o WHERE o.order = :order AND o.id = :id")
    Optional<ProductOrder> findByOrderAndProduct_Id( @Param("order") Order order, @Param("id")Integer id);
    // Delete a product order by order ID and product ID
    void deleteByOrderIdAndProductId(Integer orderId, Integer productId);
}
