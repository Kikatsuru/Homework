package net.kikatsuru.homework.api;

import net.kikatsuru.homework.data.country.Country;
import net.kikatsuru.homework.data.country.city.City;
import net.kikatsuru.homework.data.country.language.CountryLanguage;

import java.util.List;

public interface Api {
    /**
     * Logging user.
     *
     * @param login Login.
     * @param password Password.
     *
     * @return Token.
     */
    String login(String login, String password);

    /**
     * Registering user.
     *
     * @param login Login.
     * @param password Password.
     *
     * @return Token.
     */
    String register(String login, String password);

    /**
     * Logout.
     */
    void logout();

    /**
     * Get countries.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Countries.
     */
    List<Country> getCountryList(int offset, int count);

    /**
     * Update country values.
     *
     * @param country Country.
     */
    void updateCountry(Country country);

    /**
     * Delete country.
     *
     * @param country Country.
     */
    void deleteCountry(Country country);

    /**
     * Get cities.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Cities.
     */
    List<City> getCityList(int offset, int count);

    /**
     * Update city.
     *
     * @param city City.
     */
    void updateCity(City city);

    /**
     * Delete city.
     *
     * @param city City.
     */
    void deleteCity(City city);

    /**
     * Get languages.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Languages.
     */
    List<CountryLanguage> getLanguageList(int offset, int count);

    /**
     * Update language.
     *
     * @param language Language.
     */
    void updateLanguage(CountryLanguage language);

    /**
     * Delete language.
     *
     * @param language Language.
     */
    void deleteLanguage(CountryLanguage language);

    /**
     * Checks if token is valid.
     *
     * @param token Token.
     *
     * @return Valid or not.
     */
    boolean isTokenValid(String token);
}
