package org.example.demo.data.city;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
    private static final CityService service = new CityService();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "country_code")
    private String countryCode;
    private String district;
    private int population;
    public City() { }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public static CityService getService() {
        return service;
    }
}
