package com.mdms.service;

import com.mdms.model.MasterData;

import java.util.List;

public interface MasterDataService {

    public MasterData addMasterData(MasterData masterData);
    public MasterData updateMasterData(MasterData masterData);
    public MasterData getMasterData(String dateEffective);
    public List<MasterData> getAllMasterData();
    public MasterData deleteMasterData(String dateEffective);
    public void deactivatePreviousActiveEntry();
    public MasterData getActiveMasterData();
}
