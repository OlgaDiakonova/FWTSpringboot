package com.go2it.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "delivery_method")
public class DeliveryMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dm_id")
    private int dmId;
    @Column(name = "dm_name")
    private String dmName;
    @Column(name = "dm_description")
    private String dmDescription;

    public DeliveryMethod() {
    }

    public int getDmId() {
        return dmId;
    }

    public void setDmId(int dmId) {
        this.dmId = dmId;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public String getDmDescription() {
        return dmDescription;
    }

    public void setDmDescription(String dmDescription) {
        this.dmDescription = dmDescription;
    }
}
