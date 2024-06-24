package com.mdms.dao;

import com.mdms.model.MasterData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDataDao extends JpaRepository<MasterData, String> {
    MasterData findByActive(int active);
}
