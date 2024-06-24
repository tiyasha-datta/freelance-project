package com.fms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fleet")
public class Fleet {

    @Id
    @Column(name = "Tanker Number")
    private String tankerNumber;
    @Column(name = "First- Regst-")
    private String firstRegistration;
    @Column(name = "Contract Valid From")
    private String contractValidFrom;
    @Column(name = "Contract Valid TO")
    private String contractValidTo;
    @Column(name = "Transporter Code")
    private String transporterCode;
    @Column(name = "Vendor Name")
    private String vendorName;
    @Column(name = "Terminal")
    private String terminal;
    @Column(name = "Total Capacity")
    private String totalCapacity;
    @Column(name = "Dealer/Transporter")
    private String dealerTransporter;
    @Column(name = "Ownership")
    private String ownership;
    @Column(name = "Referance date")
    private String referenceDate;
    @Column(name = "Age")
    private String age;
    @Column(name = "Model")
    private String model;
    @Column(name = "Make")
    private String make;
    @Column(name = "Engine Number")
    private String engineNumber;
    @Column(name = "Chassis Number")
    private String chassisNumber;
    @Column(name = "Insurance No")
    private String insuranceNumber;
    @Column(name = "Insurance Dt")
    private String insuranceDate;
    @Column(name = "Explosive Lic No")
    private String explosiveLicNumber;
    @Column(name = "Explosive Lic Dt")
    private String explosiveLicDate;
    @Column(name = "Calib Lic No")
    private String calibLicNumber;
    @Column(name = "Calib Lic Dt")
    private String calibLicDate;
}
