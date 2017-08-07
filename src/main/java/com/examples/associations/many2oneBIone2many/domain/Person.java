package com.examples.associations.many2oneBIone2many.domain;

import javax.persistence.*;
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

    @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "owner")
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
