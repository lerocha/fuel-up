package com.github.lerocha.fuelup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by lerocha on 6/27/15.
 */
@Entity
public class VehicleLog extends BaseEntity {

    @Column
    private BigDecimal odometerStart;

    @Column
    private BigDecimal odometerEnd;

    @Column
    private BigDecimal volume;

    @Column
    private BigDecimal unitPrice;

    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;

    public BigDecimal getOdometerStart() {
        return odometerStart;
    }

    public void setOdometerStart(BigDecimal odometerStart) {
        this.odometerStart = odometerStart;
    }

    public BigDecimal getOdometerEnd() {
        return odometerEnd;
    }

    public void setOdometerEnd(BigDecimal odometerEnd) {
        this.odometerEnd = odometerEnd;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("vehicleLogId=").append(getId())
                .append("; odometerStart=").append(odometerStart)
                .append("; odometerEnd=").append(odometerEnd)
                .append("; volume=").append(volume)
                .append("; unitPrice=").append(unitPrice)
                .append("; vehicleId=").append(vehicle != null ? vehicle.getId() : null)
                .toString();
    }
}
