package net.kikatsuru.homework.api;

import net.kikatsuru.homework.Environment;
import net.kikatsuru.homework.Homework;
import net.kikatsuru.homework.data.country.Country;
import net.kikatsuru.homework.data.country.city.City;
import net.kikatsuru.homework.data.country.language.CountryLanguage;
import net.kikatsuru.homework.data.user.User;
import net.kikatsuru.homework.data.user.session.UserSession;
import net.kikatsuru.homework.utils.Encrypt;
import net.kikatsuru.homework.utils.Token;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Client api implementation.
 */
public class ClientApi implements Api {
    /**
     * Default constructor.
     */
    public ClientApi() {
        this.downloadDatabase();
    }

    /**
     * Logging user.
     *
     * @param login Login.
     * @param password Password.
     *
     * @return Token.
     */
    @Override
    public String login(String login, String password) {
        User user = User.getService().findByLoginAndPassword(login, Encrypt.sha512Encrypt(password));
        System.out.println(user);
        if (user == null) {
            return null;
        }

        Token token = new Token(login, true, 86400);
        UserSession session = new UserSession(user.getId(), token);
        UserSession.getService().persist(session);

        return token.getToken();
    }

    /**
     * Registering user.
     *
     * @param login Login.
     * @param password Password.
     *
     * @return Token.
     */
    @Override
    public String register(String login, String password) {
        if (User.getService().findByLogin(login) != null) {
            return null;
        }

        User user = new User(login, Encrypt.sha512Encrypt(password));
        Token token = new Token(login, true, 86400);
        User.getService().persist(user);

        UserSession session = new UserSession(user.getId(), token);
        UserSession.getService().persist(session);

        return token.getToken();
    }

    /**
     * Logout.
     */
    @Override
    public void logout() {
        Homework.getInstance().deleteUserProfile();
    }

    /**
     * Get countries.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Countries.
     */
    @Override
    public List<Country> getCountryList(int offset, int count) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return null;
        }
        if (count == -1) return Country.getService().findAll();
        return Country.getService().find(offset, count);
    }

    /**
     * Update country values.
     *
     * @param country Country.
     */
    @Override
    public void updateCountry(Country country) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return;
        }
        Country.getService().merge(country);
    }

    /**
     * Delete country.
     *
     * @param country Country.
     */
    @Override
    public void deleteCountry(Country country) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return;
        }
        Country.getService().delete(country);
    }

    /**
     * Get cities.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Cities.
     */
    @Override
    public List<City> getCityList(int offset, int count) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return null;
        }
        if (count == -1) return City.getService().findAll();
        return City.getService().find(offset, count);
    }

    /**
     * Update city.
     *
     * @param city City.
     */
    @Override
    public void updateCity(City city) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return;
        }
        City.getService().merge(city);
    }

    /**
     * Delete city.
     *
     * @param city City.
     */
    @Override
    public void deleteCity(City city) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return;
        }
        City.getService().delete(city);
    }

    /**
     * Get languages.
     *
     * @param offset Offset.
     * @param count Count.
     *
     * @return Languages.
     */
    @Override
    public List<CountryLanguage> getLanguageList(int offset, int count) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return null;
        }
        if (count == -1) return CountryLanguage.getService().findAll();
        return CountryLanguage.getService().find(offset, count);
    }

    /**
     * Update language.
     *
     * @param language Language.
     */
    @Override
    public void updateLanguage(CountryLanguage language) {
        if (!isTokenValid(Environment.token)) {
            this.logout();
            return;
        }
        CountryLanguage.getService().merge(language);
    }

    /**
     * Delete language.
     *
     * @param language Language.
     */
    @Override
    public void deleteLanguage(CountryLanguage language) {
        CountryLanguage.getService().delete(language);
    }

    /**
     * Checks if token is valid.
     *
     * @param token Token.
     *
     * @return Valid or not.
     */
    @Override
    public boolean isTokenValid(String token) {
        UserSession session = UserSession.getService().getByToken(token);
        return session != null && session.getExpirationTime().isAfter(LocalDateTime.now());
    }

    /**
     * Copy database file to local pc.
     */
    private void downloadDatabase() {
        try (InputStream databaseStream = ClientApi.class.getResourceAsStream("/assets/database.db")) {
            if (databaseStream == null) {
                throw new RuntimeException("Something wrong with database file in jar.");
            }
            File databaseFile = new File("database.db");

            if (!databaseFile.exists()) {
                Files.copy(databaseStream, databaseFile.toPath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
