package com.github.lerocha.fuelup.controller;

import com.github.lerocha.fuelup.domain.Vehicle;
import com.github.lerocha.fuelup.domain.VehicleLog;
import com.github.lerocha.fuelup.repository.VehicleLogRepository;
import com.github.lerocha.fuelup.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by lerocha on 9/23/16.
 */
@RestController
public class VehicleController {

    private static final String PATH_VEHICLES = "/vehicles";
    private static final String PATH_VEHICLES_ID = "/vehicles/{id}";

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleLogRepository vehicleLogRepository;

    @PostConstruct
    private void onPostConstruct() {
        vehicleRepository.save(new Vehicle("Tesla", "Model 3", 2017));
        Vehicle vehicle = new Vehicle("Audi", "A3 TDI", 2011);
        vehicle = vehicleRepository.save(vehicle);
        VehicleLog vehicleLog = new VehicleLog();
        vehicleLog.setDate(LocalDateTime.now().minusDays(7));
        vehicleLog.setOdometerStart(new BigDecimal(1000));
        vehicleLog.setOdometerEnd(new BigDecimal(1300));
        vehicleLog.setUnitPrice(new BigDecimal(2.99));
        vehicleLog.setVolume(new BigDecimal(10.2));
        vehicleLog.setVehicle(vehicle);
        vehicleLogRepository.save(vehicleLog);
    }

    @RequestMapping(value = PATH_VEHICLES, method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vehicle>> getVehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    @RequestMapping(value = PATH_VEHICLES_ID, method = RequestMethod.GET)
    public ResponseEntity<Vehicle> getVehicle(@PathVariable(name = "id") Long id) {
        return Optional.of(vehicleRepository.findOne(id))
                .map(v -> ResponseEntity.ok(v))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = PATH_VEHICLES, method = RequestMethod.POST)
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        // Validate request.
        if (vehicle.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Persist new resource.
        vehicle = vehicleRepository.save(vehicle);

        // Create the header with the location of the new resource.
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_VEHICLES_ID)
                .buildAndExpand(vehicle.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        // Create response.
        return new ResponseEntity<>(vehicle, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH_VEHICLES_ID, method = RequestMethod.PUT)
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle, @PathVariable(name = "id") Long id) {
        // Validate request.
        if (vehicle.getId() != null && vehicle.getId() != id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Set id in the resource if not set yet.
        vehicle.setId(id);

        // Persist existing resource.
        vehicle = vehicleRepository.save(vehicle);

        // Create response.
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}
