package com.go2it.springboot.entity;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private int warehouseId;
    @Column(name = "warehouse_name")
    private String warehouseName;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> productList;
    @Column(name = "weight_available")
    private double weightAvailable;

//
//    @OneToOne
//    @JoinColumn(name = "location_id")
//    private Location location;


    public Warehouse() {
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getWeightAvailable() {
        return weightAvailable;
    }

    public void setWeightAvailable(double weightAvailable) {
        this.weightAvailable = weightAvailable;
    }
}

