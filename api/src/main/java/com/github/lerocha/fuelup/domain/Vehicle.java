package com.github.lerocha.fuelup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Created by lerocha on 6/27/15.
 */
@Entity
public class Vehicle extends BaseEntity {
    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer year;

    @ManyToOne
    private User user;

    public Vehicle(String make, String model, Integer year) {
        super();
        this.make = make;
        this.model = model;
        this.year = year;
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedDate(now);
        this.setLastModifiedDate(now);
    }

    public Vehicle() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("vehicleId=").append(getId())
                .append("; make=").append(make)
                .append("; model=").append(model)
                .append("; year=").append(year)
                .append("; userId=").append(user != null ? user.getId() : null)
                .toString();
    }
}
