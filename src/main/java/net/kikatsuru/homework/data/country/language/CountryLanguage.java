package net.kikatsuru.homework.data.country.language;

import jakarta.persistence.*;
import net.kikatsuru.homework.data.country.Country;
import net.kikatsuru.homework.data.country.language.columns.CountryLanguageId;

import java.math.BigDecimal;

@Entity
@Table(name = "country_language")
public class CountryLanguage {
    private static final CountryLanguageService service = new CountryLanguageService();

    public CountryLanguage() { }

    @EmbeddedId
    private CountryLanguageId id;

    @Column(name = "is_official", nullable = false)
    private String isOfficial;

    @Column(name = "percentage", precision = 4, scale = 1, nullable = false)
    private BigDecimal percentage;

    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Country country;

    public CountryLanguageId getCountryLanguageId() {
        return this.id;
    }

    public CountryLanguage setCountryLanguageId(String countryCode, String language) {
        this.id = new CountryLanguageId(countryCode, language);
        return this;
    }

    public boolean isOfficial() {
        return this.isOfficial != null && this.isOfficial.equals("T");
    }

    public CountryLanguage setIsOfficial(boolean isOfficial) {
        this.isOfficial = isOfficial ? "T" : "F";
        return this;
    }

    public BigDecimal getPercentage() {
        return this.percentage == null ? BigDecimal.valueOf(0) : this.percentage;
    }

    public CountryLanguage setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
        return this;
    }

    public Country getCountry() {
        return this.country;
    }

    public static CountryLanguageService getService() {
        return CountryLanguage.service;
    }
}

