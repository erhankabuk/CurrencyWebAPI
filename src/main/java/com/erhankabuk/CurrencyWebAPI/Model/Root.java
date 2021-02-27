package com.erhankabuk.CurrencyWebAPI.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;

@Entity
//Table name için localhostu assign et
@Table(name="Root")
public class Root {
@Id
@GeneratedValue( strategy = GenerationType.AUTO )

    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    @JsonFormat(pattern="dd-MM-yyyy")
    public String date;
    @Column(name = "base")
    public String base;
    @Embedded
    public Rates rates;

    public Root(Long id, String date, String base, Rates rates) {
        this.id = id;
        this.date = date;
        this.base = base;
        this.rates = rates;
    }

    public Root() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

}