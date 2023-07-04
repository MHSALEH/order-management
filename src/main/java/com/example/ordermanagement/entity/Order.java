package com.example.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @Column(name = "order_at")
    private LocalDateTime orderAt;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<ProductOrder> productOrders;

}
