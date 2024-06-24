package com.tms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tripId;
    private String truckNumber;
    private String billingDocument;
    private String billingDate;
    private String shippingNumber;
    private String material;
    private String invoiceDate;
    private String status;
    private String supplyArea;
    private String receivingPlant;
    private String receivingStorageLocation;
    private String transCode;
    private String shipToPartyName;
    private String transporterName;
    private String supplyReportingNumber;
    private String shipTo;
    private String city;
    private String billingQty;
    private String receivedQty;
    private String loss_gain;
    private String shortageQuantity;
    private String materialTemp;
    private String materialDensity;
    private String roInDate;
    private String roInTime;
    private String roOutTime;
    private String roOutDate;
    private String podCreatedBy;
    private String podDate;
    private String podTime;
    private String podLastChangedBy;
    private String podLastChangedOn;
    private String invoiceTime;
    private String mgxDate;
    private String mgxTime;
    private String dispatchTemp;
    private String invoiceVolumeInBaseUOM;
    private String salesType;
    private String recoverableQtyInNatural;
    private String supplyPlant;
    private String state;
    private String customerGroup;
    private double standardKM;
    private String shipToName;
}
