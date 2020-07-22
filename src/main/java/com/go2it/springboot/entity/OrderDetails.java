package com.go2it.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    private int odId;
    private double weight;
    @Column(name = "total_price")
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
