package net.kikatsuru.homework.data.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {
    private static final CountryService service = new CountryService();

    @Id
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @Column(name = "name", length = 52, nullable = false)
    private String name;

    @Column(name = "continent", nullable = false)
    private String continent;

    @Column(name = "region", length = 26, nullable = false)
    private String region;

    @Column(name = "surface_area", precision = 10, scale = 2, nullable = false)
    private BigDecimal surfaceArea;

    @Column(name = "indep_year")
    private Integer independenceYear;

    @Column(name = "population", nullable = false)
    private int population;

    @Column(name = "life_expectancy", precision = 3, scale = 1)
    private BigDecimal lifeExpectancy;

    @Column(name = "gnp", precision = 10, scale = 2)
    private BigDecimal gnp;

    @Column(name = "gnp_old", precision = 10, scale = 2)
    private BigDecimal gnpOld;

    @Column(name = "local_name", length = 45, nullable = false)
    private String localName;

    @Column(name = "government_form", length = 45, nullable = false)
    private String governmentForm;

    @Column(name = "head_of_state", length = 60)
    private String headOfState;

    @Column(name = "capital")
    private Integer capital;

    @Column(name = "code_2", length = 2, nullable = false)
    private String code2;

    public String getCode() {
        return this.code;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public String getContinent() {
        return this.continent;
    }

    public Country setContinent(String continent) {
        this.continent = continent;
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public Country setRegion(String region) {
        this.region = region;
        return this;
    }

    public BigDecimal getSurfaceArea() {
        return this.surfaceArea == null ? BigDecimal.valueOf(0) : this.surfaceArea;
    }

    public Country setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
        return this;
    }

    public int getIndependenceYear() {
        return Objects.requireNonNullElse(this.independenceYear, 0);
    }

    public Country setIndependenceYear(int independenceYear) {
        this.independenceYear = independenceYear;
        return this;
    }

    public int getPopulation() {
        return this.population;
    }

    public Country setPopulation(int population) {
        this.population = population;
        return this;
    }

    public BigDecimal getLifeExpectancy() {
        return this.lifeExpectancy == null ? BigDecimal.valueOf(0) : this.lifeExpectancy;
    }

    public Country setLifeExpectancy(BigDecimal lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
        return this;
    }

    public BigDecimal getGnp() {
        return this.gnp == null ? BigDecimal.valueOf(0) : this.gnp;
    }

    public Country setGnp(BigDecimal gnp) {
        this.gnp = gnp;
        return this;
    }

    public BigDecimal getGnpOld() {
        return this.gnpOld == null ? BigDecimal.valueOf(0) : this.gnpOld;
    }

    public Country setGnpOld(BigDecimal gnpOld) {
        this.gnpOld = gnpOld;
        return this;
    }

    public String getLocalName() {
        return this.localName;
    }

    public Country setLocalName(String localName) {
        this.localName = localName;
        return this;
    }

    public String getGovernmentForm() {
        return this.governmentForm;
    }

    public Country setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
        return this;
    }

    public String getHeadOfState() {
        return this.headOfState;
    }

    public Country setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
        return this;
    }

    public int getCapital() {
        return this.capital == null ? 0 : this.capital;
    }

    public Country setCapital(int capital) {
        this.capital = capital;
        return this;
    }

    public String getCode2() {
        return this.code2;
    }

    public Country setCode2(String code2) {
        this.code2 = code2;
        return this;
    }

    public static CountryService getService() {
        return Country.service;
    }
}