package com.github.lerocha.fuelup.domain;

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
}
