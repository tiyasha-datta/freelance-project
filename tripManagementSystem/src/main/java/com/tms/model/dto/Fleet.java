package com.tms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Fleet {

    @JsonProperty("tankerNumber")
    private String tankerNumber;

    @JsonProperty("firstRegistration")
    private String firstRegistration;

    @JsonProperty("contractValidFrom")
    private String contractValidFrom;

    @JsonProperty("contractValidTo")
    private String contractValidTo;

    @JsonProperty("transporterCode")
    private String transporterCode;

    @JsonProperty("vendorName")
    private String vendorName;

    @JsonProperty("terminal")
    private String terminal;

    @JsonProperty("totalCapacity")
    private double totalCapacity;

    @JsonProperty("dealerTransporter")
    private String dealerTransporter;

    @JsonProperty("ownership")
    private String ownership;

    @JsonProperty("referenceDate")
    private String referenceDate;

    @JsonProperty("age")
    private String age;

    @JsonProperty("model")
    private String model;

    @JsonProperty("make")
    private String make;

    @JsonProperty("engineNumber")
    private String engineNumber;

    @JsonProperty("chassisNumber")
    private String chassisNumber;

    @JsonProperty("insuranceNumber")
    private String insuranceNumber;

    @JsonProperty("insuranceDate")
    private String insuranceDate;

    @JsonProperty("explosiveLicNumber")
    private String explosiveLicNumber;

    @JsonProperty("explosiveLicDate")
    private String explosiveLicDate;

    @JsonProperty("calibLicNumber")
    private String calibLicNumber;

    @JsonProperty("calibLicDate")
    private String calibLicDate;
}
