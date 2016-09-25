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

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by lerocha on 9/25/16.
 */
@RestController
public class VehicleLogController {

    private static final String PATH_VEHICLELOGS = "/vehicles/{vehicleId}/logs";
    private static final String PATH_VEHICLELOGS_ID = "/vehicles/{vehicleId}/logs/{id}";

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleLogRepository vehicleLogRepository;

    @RequestMapping(value = PATH_VEHICLELOGS, method = RequestMethod.GET)
    public ResponseEntity<List<VehicleLog>> getVehicleLogs(@PathVariable(name = "vehicleId") Long vehicleId) {
        return Optional.of(vehicleLogRepository.findByVehicleId(vehicleId))
                .map(v -> ResponseEntity.ok(v))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = PATH_VEHICLELOGS_ID, method = RequestMethod.GET)
    public ResponseEntity<VehicleLog> getVehicleLog(@PathVariable(name = "vehicleId") Long vehicleId,
                                                    @PathVariable(name = "id") Long id) {
        VehicleLog vehicleLog = vehicleLogRepository.findOne(id);
        if (vehicleLog == null || vehicleLog.getVehicle() == null || vehicleLog.getVehicle().getId() != vehicleId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vehicleLog);
    }

    @RequestMapping(value = PATH_VEHICLELOGS, method = RequestMethod.POST)
    public ResponseEntity<VehicleLog> createVehicleLog(@PathVariable(name = "vehicleId") Long vehicleId,
                                                       @RequestBody VehicleLog vehicleLog) {
        // Validate request.
        if (vehicleLog.getId() != null) {
            throw new IllegalArgumentException();
//
       }

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vehicleLog.setVehicle(vehicle);

        // Persist new resource.
        vehicleLog = vehicleLogRepository.save(vehicleLog);

        // Create the header with the location of the new resource.
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PATH_VEHICLELOGS_ID)
                .buildAndExpand(vehicleId, vehicleLog.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        // Create response.
        return new ResponseEntity<>(vehicleLog, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH_VEHICLELOGS_ID, method = RequestMethod.PUT)
    public ResponseEntity<VehicleLog> updateVehicleLog(@PathVariable(name = "vehicleId") Long vehicleId,
                                                       @RequestBody VehicleLog vehicleLog,
                                                       @PathVariable(name = "id") Long id) {
        // Validate request.
        if (vehicleLog.getId() != null && vehicleLog.getId() != id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vehicleLog.setVehicle(vehicle);

        // Set id in the resource if not set yet.
        vehicleLog.setId(id);

        // Persist existing resource.
        vehicleLog = vehicleLogRepository.save(vehicleLog);

        // Create response.
        return new ResponseEntity<>(vehicleLog, HttpStatus.OK);
    }
}
