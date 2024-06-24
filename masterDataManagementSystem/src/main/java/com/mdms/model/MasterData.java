package com.mdms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "freight_rates")
public class MasterData {

    @Id
    @Column(name = "date_effective")
    private String dateEffective;

    @Column(name = "rate_12_14_klkm")
    private double rate1214Klkm;

    @Column(name = "rate_18_20_klkm")
    private double rate1820Klkm;

    @Column(name = "rate_22_25_klkm")
    private double rate2225Klkm;

    @Column(name = "rate_28_29_klkm")
    private double rate2829Klkm;

    @Column(name = "fdz_rate_12_kl")
    private double fdzRate12Kl;

    @Column(name = "fdz_rate_18_20_kl")
    private double fdzRate1820Kl;

    @Column(name = "fdz_rate_22_25_kl")
    private double fdzRate2225Kl;

    @Column(name = "fdz_rate_28_29_kl")
    private double fdzRate2829Kl;

    @Column(name = "active")
    private int active = 1;
}
