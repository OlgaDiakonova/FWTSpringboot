package com.go2it.springboot.entity;
import com.go2it.springboot.util.LocalDateAttributeConverter;
import com.go2it.springboot.util.OrderDetailsPK;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "order_price")
    private double orderPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> productOrderDetails;

    public Order() {
    }

    public List<OrderDetails> getProductOrderDetails() {
        return productOrderDetails;
    }

    public void setProductOrderDetails(List<OrderDetails> productOrderDetails) {
        this.productOrderDetails = productOrderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate order_date) {
        this.orderDate = order_date;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double order_price) {
        this.orderPrice = order_price;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId() &&
                Double.compare(order.getOrderPrice(), getOrderPrice()) == 0 &&
                getOrderDate().equals(order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderDate(), getOrderPrice());
    }
}
