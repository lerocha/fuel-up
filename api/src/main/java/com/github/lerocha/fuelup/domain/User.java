package com.github.lerocha.fuelup.domain;

import javax.persistence.*;

/**
 * Created by lerocha on 6/21/15.
 */
@Entity
public class User extends BaseEntity {
    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("userId=").append(getId())
                .append("; username=").append(username)
                .append("; firstName=").append(firstName)
                .append("; lastName=").append(lastName)
                .toString();
    }
}
