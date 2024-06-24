package com.tms.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MasterData {

    @JsonProperty("dateEffective")
    private String dateEffective;

    @JsonProperty("rate1214Klkm")
    private double rate1214Klkm;

    @JsonProperty("rate1820Klkm")
    private double rate1820Klkm;

    @JsonProperty("rate2225Klkm")
    private double rate2225Klkm;

    @JsonProperty("rate2829Klkm")
    private double rate2829Klkm;

    @JsonProperty("fdzRate12Kl")
    private double fdzRate12Kl;

    @JsonProperty("fdzRate1820Kl")
    private double fdzRate1820Kl;

    @JsonProperty("fdzRate2225Kl")
    private double fdzRate2225Kl;

    @JsonProperty("fdzRate2829Kl")
    private double fdzRate2829Kl;

    @JsonProperty("active")
    private int active;
}
