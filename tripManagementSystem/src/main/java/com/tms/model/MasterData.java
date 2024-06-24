package com.tms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MasterData {

    @JsonProperty("date_effective")
    private String dateEffective;

    @JsonProperty("rate_12_14_klkm")
    private double rate1214Klkm;

    @JsonProperty("rate_18_20_klkm")
    private double rate1820Klkm;

    @JsonProperty("rate_22_25_klkm")
    private double rate2225Klkm;

    @JsonProperty("rate_28_29_klkm")
    private double rate2829Klkm;

    @JsonProperty("fdz_rate_12_kl")
    private double fdzRate12Kl;

    @JsonProperty("fdz_rate_18_20_kl")
    private double fdzRate1820Kl;

    @JsonProperty("fdz_rate_22_25_kl")
    private double fdzRate2225Kl;

    @JsonProperty("fdz_rate_28_29_kl")
    private double fdzRate2829Kl;

    @JsonProperty("active")
    private int active;
}
