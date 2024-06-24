package com.tms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Fleet {

    @JsonProperty("Tanker Number")
    private String tankerNumber;

    @JsonProperty("First- Regst-")
    private String firstRegistration;

    @JsonProperty("Contract Valid From")
    private String contractValidFrom;

    @JsonProperty("Contract Valid TO")
    private String contractValidTo;

    @JsonProperty("Transporter Code")
    private String transporterCode;

    @JsonProperty("Vendor Name")
    private String vendorName;

    @JsonProperty("Terminal")
    private String terminal;

    @JsonProperty("Total Capacity")
    private double totalCapacity; // Change to double if capacity is a number

    @JsonProperty("Dealer/Transporter")
    private String dealerTransporter;

    @JsonProperty("Ownership")
    private String ownership;

    @JsonProperty("Referance date")
    private String referenceDate;

    @JsonProperty("Age")
    private String age;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("Make")
    private String make;

    @JsonProperty("Engine Number")
    private String engineNumber;

    @JsonProperty("Chassis Number")
    private String chassisNumber;

    @JsonProperty("Insurance No")
    private String insuranceNumber;

    @JsonProperty("Insurance Dt")
    private String insuranceDate;

    @JsonProperty("Explosive Lic No")
    private String explosiveLicNumber;

    @JsonProperty("Explosive Lic Dt")
    private String explosiveLicDate;

    @JsonProperty("Calib Lic No")
    private String calibLicNumber;

    @JsonProperty("Calib Lic Dt")
    private String calibLicDate;
}
