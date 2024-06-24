package com.tms.controller;

import com.tms.model.Trip;
import com.tms.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @PostMapping("/")
    public ResponseEntity<?> addTrip(@RequestBody Trip trip) {
        logger.info("Received request to add trip: {}", trip);
        try {
            Trip newTrip = this.tripService.addTrip(trip);
            logger.info("Trip added successfully: {}", newTrip);
            return ResponseEntity.ok(newTrip);
        } catch (Exception e) {
            String errorMessage = "Error occurred while adding trip: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTrips() {
        logger.info("Received request to fetch all trips.");
        try {
            List<Trip> trips = this.tripService.getAllTrips();
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching all trips: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/truck/{truckNumber}")
    public ResponseEntity<?> getTripByTruckNumber(@PathVariable String truckNumber) {
        logger.info("Received request to fetch trip by truck number: {}", truckNumber);
        try {
            List<Trip> trips = this.tripService.getTripByTruckNumber(truckNumber);
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching trips for truck number " + truckNumber + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<?> getTripByTripId(@PathVariable String tripId) {
        logger.info("Received request to fetch trip by trip ID: {}", tripId);
        try {
            Trip trip = this.tripService.getTripByTripId(tripId);
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching trip for trip ID " + tripId + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateTrip(@RequestBody Trip trip) {
        logger.info("Received request to update trip: {}", trip);
        try {
            Trip updatedTrip = this.tripService.updateTrip(trip);
            logger.info("Trip updated successfully: {}", updatedTrip);
            return ResponseEntity.ok(updatedTrip);
        } catch (Exception e) {
            String errorMessage = "Error occurred while updating trip: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/truck/{truckNumber}")
    public ResponseEntity<?> deleteTripByTruckId(@PathVariable String truckNumber) {
        logger.info("Received request to delete trips by truck number: {}", truckNumber);
        try {
            List<Trip> deletedTrips = this.tripService.deleteTripByTruckId(truckNumber);
            logger.info("Trips deleted successfully for truck number: {}", truckNumber);
            return ResponseEntity.ok(deletedTrips);
        } catch (Exception e) {
            String errorMessage = "Error occurred while deleting trips for truck number " + truckNumber + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteTripByTripId(@PathVariable String tripId) {
        logger.info("Received request to delete trip by trip ID: {}", tripId);
        try {
            Trip deletedTrip = this.tripService.deleteTripByTripId(tripId);
            logger.info("Trip deleted successfully: {}", deletedTrip);
            return ResponseEntity.ok(deletedTrip);
        } catch (Exception e) {
            String errorMessage = "Error occurred while deleting trip for trip ID " + tripId + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/calculateTripFare/{tripId}")
    public ResponseEntity<?> calculateEachTripFare(@PathVariable String tripId) {
        logger.info("Received request to calculate fare for trip ID: {}", tripId);
        try {
            double fare = this.tripService.calculateEachTripFare(tripId);
            return ResponseEntity.ok(fare);
        } catch (Exception e) {
            String errorMessage = "Error occurred while calculating fare for trip ID " + tripId + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
