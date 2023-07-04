package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.dto.ProductOrderDTO;
import com.example.ordermanagement.entity.Customer;
import com.example.ordermanagement.entity.Order;
import com.example.ordermanagement.entity.Product;
import com.example.ordermanagement.entity.ProductOrder;
import com.example.ordermanagement.exception.ResourceNotFoundException;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.ProductOrderRepository;
import com.example.ordermanagement.repository.ProductRepository;
import com.example.ordermanagement.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductOrderDTO> getProductsOfOrder(Integer customerId, Integer orderId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));


        Order order = orderRepository.findByCustomerAndOrderId(customer,orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order ", " id ", orderId));


        // Get product orders of the order
        List<ProductOrder> productOrders = order.getProductOrders();

        // Convert to DTO
        List<ProductOrderDTO> productOrderDTOs = productOrders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return productOrderDTOs;

    }

//    @Override
//    public ProductOrderDTO getProductOfOrder(Integer customerId, Integer orderId, Integer productId) {
////        Customer customer = customerRepository.findById(customerId)
////                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));
////        System.out.println(productId);
////        System.out.println(customer);
////        Product product=productRepository.findById(productId)
////                .orElseThrow(() -> new ResourceNotFoundException("product ", " id ", productId));
//
//
//    //    System.out.println(product);
//        System.out.println(convertToDto(productOrderRepository.findByOrderIdAndProductId( customerId,orderId, productId)));
//        return convertToDto(productOrderRepository.findByOrderIdAndProductId( customerId,orderId, productId));
//    }

//    @Override
//    public ProductOrderDTO getProductOfOrder(Integer customerId, Integer orderId, Integer productId) {
//
//        ProductOrder productOrder = productOrderRepository.findByOrder_Customer_IdAndOrder_IdAndProduct_Id(customerId, orderId, productId);
//        System.out.println(productOrder);
//        if (productOrder == null) {
//            throw new ResourceNotFoundException("ProductOrder not found for customerId: " , customerId + ", orderId: " + orderId + ", productId: ",productId);
//        }
//        System.out.println(convertToDto(productOrder));
//        return convertToDto(productOrder);
//    }

//    @Override
//    public ProductOrderDTO getProductOfOrder(Integer customerId, Integer orderId, Integer productId) {
//        ProductOrder productOrder = productOrderRepository.findFirstByCustomerIdAndOrderIdAndProductId(customerId, orderId, productId)
//                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder not found for customerId: " , customerId + ", orderId: " + orderId + ", productId: ",productId));
//        return convertToDto(productOrder);
//    }


    @Override
    public ProductOrderDTO getProductOfOrder(Integer customerId, Integer orderId, Integer productOrderId) {
        // Get customer from customerId
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));

        // Get order from orderId that belongs to the customer
        Order order = orderRepository.findByCustomerAndOrderId(customer, orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order ", " id ", orderId));

        // Get productOrder from productId that belongs to the order
        ProductOrder productOrder = productOrderRepository.findByOrderAndProduct_Id(order, productOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder ", "productOrderId", productOrderId));

        // Convert to DTO
        ProductOrderDTO productOrderDTO = convertToDto(productOrder);

        return productOrderDTO;
    }



//    @Override
//    public ProductOrderDTO addProductToOrder(Integer customerId, Integer orderId, ProductOrderDTO productOrderDTO) {
//
//
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));
//
////        todo
////        return productOrderRepository.(orderId, productOrderDTO);
////        same of update
//        return null;
//    }

    @Override
    public ProductOrderDTO addProductToOrder(Integer customerId, Integer orderId, ProductOrderDTO productOrderDTO) {
        // Find customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        // Find order and confirm it belongs to the customer
        Order order = orderRepository.findByCustomerAndOrderId(customer,orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        // Find product
        Product product = productRepository.findById(productOrderDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productOrderDTO.getProductId()));

        BigDecimal quantity = new BigDecimal(productOrderDTO.getQuantity());
        BigDecimal price=product.getPrice().multiply(quantity);
        BigDecimal vat=product.getVat().multiply(price);

        // Create new ProductOrder and set its properties
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setOrder(order);
        productOrder.setQuantity(productOrderDTO.getQuantity());
        productOrder.setPrice(price.add(vat));
        productOrder.setVat(vat);
        // Save ProductOrder in the repository
        ProductOrder savedProductOrder = productOrderRepository.save(productOrder);
        System.out.println("save product order->" + savedProductOrder);
        // Convert saved ProductOrder to ProductOrderDTO and return it
        return convertToDto(savedProductOrder);
    }


//    @Override
//    public ProductOrderDTO updateProductOfOrder(Integer customerId, Integer orderId, Integer productId, ProductOrderDTO productOrderDTO) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));
//
//        ProductOrderDTO poDTO = productOrderRepository.findByOrderIdAndProductId(orderId, productId);
////        todo
//        ProductOrder productOrder = convertToEntity(poDTO);
//
//        //update all attributes and save
//        return productOrderRepository.save(productOrder);
//
//
//    }

//    @Override
//    public ProductOrderDTO updateProductOfOrder(Integer customerId, Integer orderId, Integer productId, ProductOrderDTO productOrderDTO) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));
//
//        ProductOrder productOrder = productOrderRepository.findByOrderIdAndProductId(orderId, productId)
//                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "orderId " + orderId + " productId" + productId, orderId));
//
//        // Update all attributes of productOrder with those of productOrderDTO
//        // Assuming setter methods are available
//        productOrder.setQuantity(productOrderDTO.getQuantity());
//        productOrder.setPrice(productOrderDTO.getPrice());
//        productOrder.setVat(productOrderDTO.getVat());
//        // For product and order, you should use their respective services or repositories to fetch by Id
//        // Example:
//        // productOrder.setProduct(productRepository.findById(productOrderDTO.getProductId()));
//        // productOrder.setOrder(orderRepository.findById(productOrderDTO.getOrderId()));
//
//        ProductOrder updatedProductOrder = productOrderRepository.save(productOrder);
//
//        // Convert saved ProductOrder entity back to DTO
//        ProductOrderDTO updatedProductOrderDTO = convertToDto(updatedProductOrder);
//
//        return updatedProductOrderDTO;
//    }

    @Override
    public ProductOrderDTO updateProductOfOrder(Integer customerId, Integer orderId, Integer productId, ProductOrderDTO productOrderDTO) {

        // Get customer from customerId
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));

        // Get order from orderId that belongs to the customer
        Order order = orderRepository.findByCustomerAndOrderId(customer, orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order ", " id ", orderId));

        // Get productOrder from productId that belongs to the order
        ProductOrder productOrder = productOrderRepository.findByOrderAndProduct_Id(order, productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder ", "productOrderId", productId));

        // Update ProductOrder attributes
//        productOrder.setProduct(product);
//        productOrder.setOrder(order);
        productOrder.setQuantity(productOrderDTO.getQuantity());
        productOrder.setPrice(productOrderDTO.getPrice());
        productOrder.setVat(productOrderDTO.getVat());

        // Save ProductOrder in the repository
        ProductOrder updatedProductOrder = productOrderRepository.save(productOrder);

        // Convert updated ProductOrder to ProductOrderDTO and return it
        return convertToDto(updatedProductOrder);
    }


    private ProductOrderDTO convertToDto(ProductOrder productOrder) {
        ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        if (productOrder.getProduct() != null) {
            productOrderDTO.setProductId(productOrder.getProduct().getId());
        }
        if (productOrder.getOrder() != null) {
            productOrderDTO.setOrderId(productOrder.getOrder().getId());
        }
        productOrderDTO.setId(productOrder.getId());
        productOrderDTO.setQuantity(productOrder.getQuantity());
        productOrderDTO.setPrice(productOrder.getPrice());
        productOrderDTO.setVat(productOrder.getVat());

        return productOrderDTO;
    }


    @Override
    public void deleteProductOfOrder(Integer customerId, Integer orderId, Integer productId) {
        // Get customer from customerId
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " id ", customerId));

        // Get order from orderId that belongs to the customer
        Order order = orderRepository.findByCustomerAndOrderId(customer, orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order ", " id ", orderId));

        // Get productOrder from productId that belongs to the order
        ProductOrder productOrder = productOrderRepository.findByOrderAndProduct_Id(order, productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder ", "productOrderId", productId));

        // Delete productOrder
        productOrderRepository.delete(productOrder);
    }




    public ProductOrder convertToEntity(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();

        if (productOrderDTO.getProductId() != null) {
            Product product = productRepository.findById(productOrderDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found", "productId", productOrderDTO.getProductId()));
            productOrder.setProduct(product);
        }

        if (productOrderDTO.getOrderId() != null) {
            Order order = orderRepository.findById(productOrderDTO.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("order not found", "orderId", productOrderDTO.getOrderId()));
            productOrder.setOrder(order);
        }
        productOrder.setId(productOrderDTO.getId());
        productOrder.setQuantity(productOrderDTO.getQuantity());
        productOrder.setPrice(productOrderDTO.getPrice());
        productOrder.setVat(productOrderDTO.getVat());
        return productOrder;


    }





}
//    @Override
//    public List<ProductOrderDTO> getOrdersOfProduct(Integer customerId, Integer productId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        return productOrderRepository.findOrdersByProductAndCustomerId(productId, customerId);
//    }

//    @Override
//    public ProductOrderDTO getOrderOfProduct(Integer customerId, Integer productId, Integer orderId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        return productOrderRepository.findOrderByProductAndOrderAndCustomerId(productId, orderId, customerId);
//    }

//    @Override
//    public ProductOrderDTO addOrderToProduct(Integer customerId, Integer productId, ProductOrderDTO productOrderDTO) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        return productOrderRepository.addOrderToProduct(productId, productOrderDTO);
//    }
//
//    @Override
//    public ProductOrderDTO updateOrderOfProduct(Integer customerId, Integer productId, Integer orderId, ProductOrderDTO productOrderDTO) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        return productOrderRepository.updateOrderOfProduct(productId, orderId, productOrderDTO);
//    }

//    @Override
//    public void deleteOrderOfProduct(Integer customerId, Integer productId, Integer orderId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        productOrderRepository.deleteOrderOfProduct(productId, orderId);
//    }



//    @Override
//    public void deleteOrderOfProduct(Integer customerId, Integer productId, Integer orderId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer "," id " , customerId));
//
//        Order order = orderRepository.findByCustomerAndOrderId(customer,orderId)
//                .orElseThrow(() -> new ResourceNotFoundException("Order "," id " , orderId));
//
//        ProductOrder productOrder = productOrderRepository.findByOrderIdAndProductId(orderId, productId)
//                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder","orderId"+ orderId +"productId" +productId, productId));
//
//        productOrderRepository.delete(productOrder);
//    }

