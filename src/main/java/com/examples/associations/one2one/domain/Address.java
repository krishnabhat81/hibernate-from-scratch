package com.examples.associations.one2one.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by krishna1bhat on 8/7/17.
 */
@Entity
public class Address {
    @Id @GeneratedValue
    private long id; // if you use @PrimaryKeyJoinColumn in Customer; you have to set id manually
    private String street;
    private String city;
    private String state;
    private int zip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
