package com.mdms.service.impl;

import com.mdms.dao.MasterDataDao;
import com.mdms.exception.MasterDataNotFoundException;
import com.mdms.model.MasterData;
import com.mdms.service.MasterDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    private static final Logger logger = LoggerFactory.getLogger(MasterDataServiceImpl.class);

    @Autowired
    private MasterDataDao masterDataDao;
    @Override
    public MasterData addMasterData(MasterData masterData) {
        logger.info("Adding new master data: {}", masterData);
        return this.masterDataDao.save(masterData);
    }

    @Override
    public MasterData updateMasterData(MasterData masterData) {
        logger.info("Updating master data: {}", masterData);
        return this.masterDataDao.save(masterData);
    }

    @Override
    public MasterData getMasterData(String dateEffective) {
        logger.info("Fetching master data with data effective: {}", dateEffective);
        return this.masterDataDao.findById(dateEffective)
                .orElseThrow(() -> new MasterDataNotFoundException("Master Data not found with date effective: " + dateEffective));
    }

    @Override
    public List<MasterData> getAllMasterData() {
        logger.info("Fetching all master data.");
        return this.masterDataDao.findAll();
    }

    @Override
    public MasterData deleteMasterData(String dateEffective) {
        logger.info("Updating master data with date effective: {}", dateEffective);
        MasterData masterData = this.masterDataDao.findById(dateEffective)
                .orElseThrow(() -> new MasterDataNotFoundException("Master Data not found with date effective: " + dateEffective));
        this.masterDataDao.deleteById(dateEffective);
        return masterData;
    }

    @Override
    public void deactivatePreviousActiveEntry() {
        logger.info("Deactivating previous active entries.");
        MasterData activeEntry = this.masterDataDao.findByActive(1);

        if (activeEntry == null) {
            logger.info("No active entries found to deactivate.");
        } else {
            activeEntry.setActive(0);
            this.masterDataDao.save(activeEntry);
            logger.info("Previous active entry deactivated successfully.");
        }
    }

    @Override
    public MasterData getActiveMasterData() {
        logger.info("Fetching active master data.");
        return this.masterDataDao.findByActive(1);
    }
}
