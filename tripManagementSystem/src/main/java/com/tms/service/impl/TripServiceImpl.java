package com.tms.service.impl;

import com.tms.dao.TripDao;
import com.tms.exception.TripNotFoundException;
import com.tms.model.Fleet;
import com.tms.model.MasterData;
import com.tms.model.Trip;
import com.tms.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);
    @Autowired
    private TripDao tripDao;

    @Override
    public Trip addTrip(Trip trip) {
        logger.info("Adding new trip details: {}", trip);
        return this.tripDao.save(trip);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        logger.info("Updating trip details: {}", trip);
        return tripDao.save(trip);
    }

    @Override
    public List<Trip> getTripByTruckNumber(String truckNumber) {

        logger.info("Fetching trip details with truckNumber: {}", truckNumber);
        List<Trip> trips = this.tripDao.findTripByTruckNumber(truckNumber);
        if (trips.isEmpty()) {
            logger.warn("No trips found for truck number: {}", truckNumber);
            throw new TripNotFoundException("Trip details not found with truckNumber: " + truckNumber);
        }
        return trips;
    }

    @Override
    public Trip getTripByTripId(String tripId) {
        logger.info("Fetching trip details with trip id: {}", tripId);
        return this.tripDao.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip details not found with trip id: " + tripId));
    }

    @Override
    public List<Trip> getAllTrips() {
        logger.info("Fetching all trips");
        return this.tripDao.findAll();
    }

    @Override
    public List<Trip> deleteTripByTruckId(String truckNumber) {
        logger.info("Deleting trips for truck number: {}", truckNumber);
        List<Trip> trips = this.tripDao.findTripByTruckNumber(truckNumber);

        if (trips.isEmpty()) {
            logger.warn("No trips found to delete for truck number: {}", truckNumber);
            throw new TripNotFoundException("No trips found to delete for truck number: " + truckNumber);
        }

        for (Trip trip : trips) {
            this.tripDao.delete(trip);
        }

        logger.info("Trips deleted successfully for truck number: {}", truckNumber);
        return trips;
    }


    @Override
    public Trip deleteTripByTripId(String tripId) {

        logger.info("Updating trip details with trip id: {}", tripId);
        Trip trip = this.tripDao.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip details not found with trip id: " + tripId));
        this.tripDao.deleteById(tripId);
        return trip;
    }

    @Override
    public double calculateEachTripFare(String tripId) {
        logger.info("Calculating fare for trip ID: {}", tripId);

        //Fetch the trip details by trip id and then fetch the tanker number from trip details
        Trip trip = tripDao.findById(tripId).get();
        if (trip == null) {
            throw new RuntimeException("Trip details not found for tripId: " + tripId);
        }
        String tankerNumber = trip.getTruckNumber();

        // Fetch the fleet details from FMS repo using truck number or tanker number and save it in FleetDTO
        ResponseEntity<Fleet> fleetResponse = this.restTemplate.getForEntity("http://localhost:8082/fleet/" + tankerNumber, Fleet.class);
        if (fleetResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to fetch Fleet details. Status code: " + fleetResponse.getStatusCodeValue());
        }

        Fleet fleet = fleetResponse.getBody();
        if (fleet == null) {
            throw new RuntimeException("Fleet details not found for tanker number: " + tankerNumber);
        }

        // Fetch the freight rate from master data repo using active master data endpoint and save it in MasterDataDTO
        ResponseEntity<MasterData> masterDataResponse = this.restTemplate.getForEntity("http://localhost:8080/masterData/active", MasterData.class);
        if (masterDataResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to fetch Active master data. Status code: " + masterDataResponse.getStatusCodeValue());
        }

        MasterData masterData = masterDataResponse.getBody();
        if (masterData == null) {
            throw new RuntimeException("Active master data not found");
        }

        // From FleetDTO, fetch the capacity
        double capacity = fleet.getTotalCapacity();

        // Calculate RTKM = standard Km (present in TMS) * 2
        double standardKm = trip.getStandardKM();
        double rtkm = standardKm * 2;

        // Fetch FDZ rate based on capacity from MasterDataDTO
        double fdzRate = getFdzRate(capacity, masterData);

        // Calculate fare up to 49RTKM = FDZ rate * capacity
        double fareUpTo49RTKM = fdzRate * capacity;

        // Calculate remainingRTKM = RTKM - 49
        double remainingRTKM = rtkm - 49;

        // Fetch per KLKM rate based on capacity from MasterDataDTO
        double perKLKMRate = getPerKLKMRate(capacity, masterData);

        // Calculate remaining fare = capacity * remainingRTKM * perKLKM
        double remainingFare = capacity * remainingRTKM * perKLKMRate;

        // Calculate total fare = fare up to 49RTKM + remaining fare
        double totalFare = fareUpTo49RTKM + remainingFare;

        return totalFare;
    }

    private double getFdzRate(double capacity, MasterData masterData) {
        if (capacity >= 12 && capacity <= 14) {
            return masterData.getFdzRate12Kl();
        } else if (capacity >= 18 && capacity <= 20) {
            return masterData.getFdzRate1820Kl();
        } else if (capacity >= 22 && capacity <= 25) {
            return masterData.getFdzRate2225Kl();
        } else if (capacity >= 28 && capacity <= 29) {
            return masterData.getFdzRate2829Kl();
        } else {
            throw new RuntimeException("FDZ rate not found for capacity: " + capacity);
        }
    }

    private double getPerKLKMRate(double capacity, MasterData masterData) {
        if (capacity >= 12 && capacity <= 14) {
            return masterData.getRate1214Klkm();
        } else if (capacity >= 18 && capacity <= 20) {
            return masterData.getRate1820Klkm();
        } else if (capacity >= 22 && capacity <= 25) {
            return masterData.getRate2225Klkm();
        } else if (capacity >= 28 && capacity <= 29) {
            return masterData.getRate2829Klkm();
        } else {
            throw new RuntimeException("Per KLKM rate not found for capacity: " + capacity);
        }
    }


}
