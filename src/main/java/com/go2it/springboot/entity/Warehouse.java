package com.go2it.springboot.entity;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouse_id;
    private String warehouseName;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> productList;
    private double weight_available;

//
//    @OneToOne
//    @JoinColumn(name = "location_id")
//    private Location location;

    public Warehouse() {

    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public double getWeight_available() {
        return weight_available;
    }

    public void setWeight_available(double weight_available) {
        this.weight_available = weight_available;
    }

}

