package com.examples.associations.one2one.domain;

import javax.persistence.*;

/**
 * Created by krishna1bhat on 8/7/17.
 */
@Entity
public class Customer {
    @Id @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    //@PrimaryKeyJoinColumn //if you want share PK; also you have to give manual ID to Address object to -- not recommended...
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
