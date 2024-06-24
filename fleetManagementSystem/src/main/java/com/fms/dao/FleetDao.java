package com.fms.dao;

import com.fms.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetDao extends JpaRepository<Fleet, String> {
}
