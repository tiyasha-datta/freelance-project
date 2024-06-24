package com.mdms.controller;

import com.mdms.model.MasterData;
import com.mdms.service.MasterDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterData")
public class MasterDataController {

    private static final Logger logger = LoggerFactory.getLogger(MasterDataController.class);

    @Autowired
    private MasterDataService masterDataService;

    @PostMapping("/")
    public ResponseEntity<?> addMasterData(@RequestBody MasterData masterData) {
        logger.info("Received request to add master data: {}", masterData);
        try {
            this.masterDataService.deactivatePreviousActiveEntry();
            MasterData newMasterData = this.masterDataService.addMasterData(masterData);
            logger.info("Master data added successfully: {}", newMasterData);
            return ResponseEntity.ok(newMasterData);
        } catch (Exception e) {
            String errorMessage = "Error occurred while adding master data: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/{dateEffective}")
    public ResponseEntity<?> getMasterData(@PathVariable String dateEffective) {
        logger.info("Received request to fetch master data with effective date: {}", dateEffective);
        try {
            MasterData masterData = this.masterDataService.getMasterData(dateEffective);
            return ResponseEntity.ok(masterData);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching master data for date " + dateEffective + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateMasterData(@RequestBody MasterData masterData) {
        logger.info("Received request to update master data: {}", masterData);
        try {
            MasterData updatedMasterData = this.masterDataService.updateMasterData(masterData);
            logger.info("Master data updated successfully: {}", updatedMasterData);
            return ResponseEntity.ok(updatedMasterData);
        } catch (Exception e) {
            String errorMessage = "Error occurred while updating master data: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMasterData() {
        logger.info("Received request to fetch all master data.");
        try {
            List<MasterData> masterDataList = this.masterDataService.getAllMasterData();
            return ResponseEntity.ok(masterDataList);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching all master data: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveMasterData() {
        logger.info("Received request to fetch active master data.");
        try {
            MasterData activeMasterData = this.masterDataService.getActiveMasterData();
            return ResponseEntity.ok(activeMasterData);
        } catch (Exception e) {
            String errorMessage = "Error occurred while fetching active master data: " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/{dateEffective}")
    public ResponseEntity<?> deleteMasterData(@PathVariable String dateEffective) {
        logger.info("Received request to delete master data with effective date: {}", dateEffective);
        try {
            MasterData deletedMasterData = this.masterDataService.deleteMasterData(dateEffective);
            logger.info("Master data deleted successfully: {}", deletedMasterData);
            return ResponseEntity.ok(deletedMasterData);
        } catch (Exception e) {
            String errorMessage = "Error occurred while deleting master data for date " + dateEffective + ": " + e.getMessage();
            logger.error(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}


