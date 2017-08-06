package com.examples.associations.one2many.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishna1bhat on 8/5/17.
 */

@Entity
public class Person {
    @Id @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @OneToMany
    //@JoinColumn(name = "person_id")
    @Cascade(CascadeType.ALL)
    //this JoinTable is optional, but its better to use and it will create 3rd intermediate table Person_Table -- good design -- normalization
    @JoinTable(name = "Person_Car",
        joinColumns = {@JoinColumn(name = "person_id")},
        inverseJoinColumns = {@JoinColumn(name = "car_id")}
    )
    private List<Car> cars = new ArrayList<>();

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

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
