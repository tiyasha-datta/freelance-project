package com.fms.service.impl;

import com.fms.dao.FleetDao;
import com.fms.exception.FleetNotFoundException;
import com.fms.model.Fleet;
import com.fms.service.FleetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleetServiceImpl implements FleetService {

    private static final Logger logger = LoggerFactory.getLogger(FleetServiceImpl.class);

    @Autowired
    private FleetDao fleetDao;
    @Override
    public Fleet addFleet(Fleet fleet) {
        logger.info("Adding new fleet: {}", fleet);
        return this.fleetDao.save(fleet);
    }

    @Override
    public Fleet updateFleet(Fleet fleet) {
        logger.info("Updating fleet: {}", fleet);
        return this.fleetDao.save(fleet);
    }

    @Override
    public Fleet getFleet(String tankerNumber) {
        logger.info("Fetching fleet with tanker number: {}", tankerNumber);
        return this.fleetDao.findById(tankerNumber)
                .orElseThrow(() -> new FleetNotFoundException("Fleet not found with tanker number: " + tankerNumber));
    }

    @Override
    public List<Fleet> getAllFleets() {
        logger.info("Fetching all fleets.");
        return this.fleetDao.findAll();
    }

    @Override
    public Fleet deleteFleet(String tankerNumber) {

        logger.info("Deleting fleet with tanker number: {}", tankerNumber);
        Fleet fleet = this.fleetDao.findById(tankerNumber)
                .orElseThrow(() -> new FleetNotFoundException("Fleet not found with tanker number: " + tankerNumber));
        this.fleetDao.deleteById(tankerNumber);
        return fleet;
    }
}
