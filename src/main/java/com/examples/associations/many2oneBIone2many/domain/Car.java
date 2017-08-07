package com.examples.associations.many2oneBIone2many.domain;

import javax.persistence.*;

/**
 * Created by krishna1bhat on 8/5/17.
 */
@Entity
public class Car {
    @Id @GeneratedValue
    private long id;
    private short year;
    private String model;
    private String maker;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
