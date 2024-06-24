package com.tms.dao;

import com.tms.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripDao extends JpaRepository<Trip, String> {

    public List<Trip> findTripByTruckNumber(String truckNumber);
}
