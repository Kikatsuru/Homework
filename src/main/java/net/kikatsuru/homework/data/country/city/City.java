package net.kikatsuru.homework.data.country.city;

import jakarta.persistence.*;
import net.kikatsuru.homework.data.country.Country;

@Entity
@Table(name = "city")
public class City {
    private static final CityService service = new CityService();

    public City() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 35, nullable = false)
    private String name;

    @Column(name = "country_code", length = 3, nullable = false)
    private String countryCode;

    @Column(name = "district", length = 20, nullable = false)
    private String district;

    @Column(name = "population", nullable = false)
    private int population;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Country country;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public City setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getDistrict() {
        return this.district;
    }

    public City setDistrict(String district) {
        this.district = district;
        return this;
    }

    public int getPopulation() {
        return this.population;
    }

    public City setPopulation(int population) {
        this.population = population;
        return this;
    }

    public Country getCountry() {
        return this.country;
    }

    public static CityService getService() {
        return City.service;
    }
}
