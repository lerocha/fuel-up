package com.github.lerocha.fuelup.repository;

import com.github.lerocha.fuelup.domain.VehicleLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lerocha on 9/24/16.
 */
public interface VehicleLogRepository extends CrudRepository<VehicleLog, Long> {
    List<VehicleLog> findByVehicleId(Long vehicleId);
}
