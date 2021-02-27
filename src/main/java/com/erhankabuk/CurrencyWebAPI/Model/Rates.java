package com.erhankabuk.CurrencyWebAPI.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Embeddable;

@Embeddable
public class Rates {

    @JsonProperty("TRY")
    public double tRY;

    public Rates(double tRY) {
        this.tRY = tRY;
    }

    public Rates() {

    }

    public double gettRY() {
        return tRY;
    }

    public Rates settRY(double tRY) {
        this.tRY = tRY;
        return null;
    }

}