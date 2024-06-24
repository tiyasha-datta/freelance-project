package com.tms.service;

import com.tms.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {

    public Trip addTrip(Trip trip);
    public Trip updateTrip(Trip trip);
    public List<Trip> getTripByTruckNumber(String truckNumber);
    public Trip getTripByTripId(String tripId);
    public List<Trip> getAllTrips();
    public List<Trip> deleteTripByTruckId(String truckNumber);
    public Trip deleteTripByTripId(String tripId);
    public double calculateEachTripFare(String tripId);
}
