package com.fms.service;

import com.fms.model.Fleet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FleetService {

    public Fleet addFleet(Fleet fleet);
    public Fleet updateFleet(Fleet fleet);
    public Fleet getFleet(String tankerNumber);
    public List<Fleet> getAllFleets();
    public Fleet deleteFleet(String tankerNumber);
}
