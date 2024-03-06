package net.kikatsuru.homework.data.country.language.columns;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CountryLanguageId implements Serializable {

    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "language", length = 30)
    private String language;

    private CountryLanguageId() { }

    public CountryLanguageId(String countryCode, String language) {
        this.setCountryCode(countryCode);
        this.setLanguage(language);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public CountryLanguageId setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getLanguage() {
        return this.language;
    }

    public CountryLanguageId setLanguage(String language) {
        this.language = language;
        return this;
    }
}
