package com.examples.associations.many2one.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by krishna1bhat on 8/5/17.
 */
@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private short year;
    private String model;
    private String maker;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    //@JoinColumn(name = "customer_id") //optional for ManyToOne
    @JoinTable(name = "Car_Customer")//try commenting this line -- will create only tow tables
    //NOTE: Car may not have Person, so there will be a change of null customer_id in Car table
    //So, best way to create 3rd intermediate table Car_Customer using @JoinTable...
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
