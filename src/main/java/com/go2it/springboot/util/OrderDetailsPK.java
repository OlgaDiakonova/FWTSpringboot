package com.go2it.springboot.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsPK implements Serializable {

//    @Column(name = "product_id")
    private int product_id;

//    @Column(name = "order_id")
    private int order_id;

    public OrderDetailsPK() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailsPK)) return false;
        OrderDetailsPK that = (OrderDetailsPK) o;
        return getProduct_id() == that.getProduct_id() &&
                getOrder_id() == that.getOrder_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct_id(), getOrder_id());
    }
}
