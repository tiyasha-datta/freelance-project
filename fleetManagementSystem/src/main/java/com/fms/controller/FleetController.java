package com.fms.controller;

import com.fms.model.Fleet;
import com.fms.service.FleetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fleet")
public class FleetController {

    private static final Logger logger = LoggerFactory.getLogger(FleetController.class);

    @Autowired
    private FleetService fleetService;

    @PostMapping("/")
    public ResponseEntity<?> addFleet(@RequestBody Fleet fleet){
        logger.info("Received request to add fleet: {}", fleet);
        try {
            Fleet newFleet = this.fleetService.addFleet(fleet);
            logger.info("Fleet added successfully: {}", newFleet);
            return ResponseEntity.ok(newFleet);
        } catch (Exception e) {
            String errorMessage = "Error occurred while adding fleet: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllFleets(){
        logger.info("Received request to fetch all fleets.");
        try {
            return ResponseEntity.ok(this.fleetService.getAllFleets());
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching all fleets: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/{tankerNumber}")
    public ResponseEntity<?> getFleet(@PathVariable String tankerNumber){

        logger.info("Received request to fetch fleet with tanker number: {}", tankerNumber);
        try {
            Fleet fleet = this.fleetService.getFleet(tankerNumber);
            return ResponseEntity.ok(fleet);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching fleet " + tankerNumber + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateFleet(@RequestBody Fleet fleet){

        logger.info("Received request to update fleet: {}", fleet);
        try {
            Fleet updatedFleet = this.fleetService.updateFleet(fleet);
            logger.info("Fleet updated successfully: {}", updatedFleet);
            return ResponseEntity.ok(updatedFleet);
        } catch (Exception e) {
            String errorMessage = "Error occurred while updating fleet: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/{tankerNumber}")
    public ResponseEntity<?> deleteFleet(@PathVariable String tankerNumber){

        logger.info("Received request to delete fleet with tanker number: {}", tankerNumber);
        try {
            Fleet deletedFleet = this.fleetService.deleteFleet(tankerNumber);
            logger.info("Fleet deleted successfully: {}", deletedFleet);
            return ResponseEntity.ok(deletedFleet);
        } catch (Exception e) {
            String errorMessage = "Error occurred while deleting fleet " + tankerNumber + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

    }



}
