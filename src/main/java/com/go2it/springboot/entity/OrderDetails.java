package com.go2it.springboot.entity;

import com.go2it.springboot.util.OrderDetailsPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable{
    @EmbeddedId
    private OrderDetailsPK id;

    private double weight;
    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetails() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 &&
                Double.compare(that.getTotalPrice(), getTotalPrice()) == 0 &&
                getProduct().equals(that.getProduct()) &&
                getOrder().equals(that.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeight(), getTotalPrice(), getProduct(), getOrder());
    }
}
