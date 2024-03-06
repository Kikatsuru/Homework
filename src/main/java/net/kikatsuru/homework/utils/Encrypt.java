package net.kikatsuru.homework.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class for encryption.
 */
public class Encrypt {

    /**
     * Encrypt to sha512.
     *
     * @param input String.
     *
     * @return Encrypted string.
     */
    public static String sha512Encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder(no.toString(16));
            while (hash.length() < 32) {
                hash.insert(0, "0");
            }
            return hash.toString();
        }
        catch (NoSuchAlgorithmException e) { // You don't have sha512 haha
            throw new RuntimeException(e);
        }
    }

}
