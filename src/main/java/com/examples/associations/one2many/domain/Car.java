package com.examples.associations.one2many.domain;

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
}
