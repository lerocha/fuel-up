package com.github.lerocha.fuelup.repository;

import com.github.lerocha.fuelup.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lerocha on 9/24/16.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
