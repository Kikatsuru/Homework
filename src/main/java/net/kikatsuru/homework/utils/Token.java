package net.kikatsuru.homework.utils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * Basic token implementation class.
 */
public class Token {
    /**
     * Token time date format.
     */
    private static final DateTimeFormatter TIMEDATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Token mask length.
     */
    private static final int MASK_LENGTH = 4;

    /**
     * Owner username.
     */
    private String userName;

    /**
     * Token.
     */
    private final String token;

    /**
     * Token mask.
     */
    private final String mask;

    /**
     * Is token expiring?
     */
    private boolean expiring;

    /**
     * Token creation time.
     */
    private LocalDateTime creationTime;

    /**
     * Token expiration time.
     */
    private LocalDateTime expirationTime;

    /**
     * Token decode constructor.
     *
     * @param token Token.
     * @param mask Token mask.
     */
    public Token(String token, String mask) {
        this.mask = mask;
        this.token = token;
        this.decodeToken(token);
    }

    /**
     * Token encode constructor.
     *
     * @param userName Owner username.
     * @param expiring Is token expiring?
     * @param seconds seconds to expire.
     */
    public Token(String userName, boolean expiring, long seconds) {
        this.userName = userName;
        this.mask = this.generateMask();
        this.expiring = expiring;
        if (expiring) {
            this.creationTime = LocalDateTime.now();
            this.expirationTime = this.creationTime.plusSeconds(seconds);
        }
        this.token = this.encodeToken(userName);
    }

    /**
     * Encode token.
     *
     * @param name Owner username.
     *
     * @return Token.
     */
    private String encodeToken(String name) {
        StringBuilder token = new StringBuilder(name + "@");

        String tokenBase = this.generateString();
        if (this.expiring) {
            token.append("expiring=true@")
                    .append(this.creationTime.format(TIMEDATE_FORMAT)).append("@")
                    .append(this.expirationTime.format(TIMEDATE_FORMAT)).append("@");
        } else {
            token.append("@expiring=false@");
        }

        token.append(tokenBase);
        token = new StringBuilder(this.maskString(token.toString()));
        return token.toString();
    }

    /**
     * Decode token.
     *
     * @param token Token.
     */
    private void decodeToken(String token) {
        token = unmaskString(token);
        String[] tokenParts = token.split("@");
        this.userName = tokenParts[0];
        this.expiring = Boolean.parseBoolean(tokenParts[1].split("=")[1]);

        if (this.expiring) {
            this.creationTime = LocalDateTime.parse(tokenParts[2], TIMEDATE_FORMAT);
            this.expirationTime = LocalDateTime.parse(tokenParts[3], TIMEDATE_FORMAT);
        }
    }

    /**
     * Generate random mask.
     *
     * @return Mask.
     */
    private String generateMask() {
        Random random = new Random();
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < MASK_LENGTH; i++) {
            mask.append(random.nextInt(10));
        }
        return mask.toString();
    }

    /**
     * Generate random string from uuid.
     *
     * @return String.
     */
    private String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Mask string.
     *
     * @param string String.
     *
     * @return Masked string.
     */
    private String maskString(String string) {
        return maskString(string, this.mask);
    }

    /**
     * Mask string.
     *
     * @param string String.
     * @param mask Mask.
     *
     * @return Masked string.
     */
    private static String maskString(String string, String mask) {
        byte[] byteString = string.getBytes(StandardCharsets.UTF_8);
        return java.util.Base64.getEncoder().encodeToString(applyMask(byteString, mask));
    }

    /**
     * Unmask string.
     *
     * @param string String.
     *
     * @return Unmasked string.
     */
    private String unmaskString(String string) {
        return unmaskString(string, this.mask);
    }

    /**
     * Unmask string.
     *
     * @param string String.
     * @param mask Mask.
     *
     * @return Unmasked string.
     */
    private static String unmaskString(String string, String mask) {
        byte[] maskedBytes = java.util.Base64.getDecoder().decode(string);
        return new String(applyMask(maskedBytes, mask), StandardCharsets.UTF_8);
    }

    /**
     * Set mask to bytes.
     *
     * @param byteString String bytes.
     * @param mask Mask.
     *
     * @return Masked bytes.
     */
    private static byte[] applyMask(byte[] byteString, String mask) {
        byte[] byteMask = mask.getBytes(StandardCharsets.UTF_8);

        byte[] resultBytes = new byte[byteString.length];
        for (int i = 0; i < byteString.length; i++) {
            resultBytes[i] = (byte) (byteString[i] ^ byteMask[i % byteMask.length]);
        }
        return resultBytes;
    }

    /**
     * @return Expiring or not.
     */
    public boolean isExpiring() {
        return this.expiring;
    }

    /**
     * @return Token.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * @return Creation time.
     */
    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    /**
     * @return Expiration time.
     */
    public LocalDateTime getExpirationTime() {
        return this.expirationTime;
    }

    /**
     * @return Valid or not.
     */
    public boolean isValid() {
        return this.expirationTime.isBefore(LocalDateTime.now());
    }

    /**
     * @return Mask.
     */
    public String getMask() {
        return this.mask;
    }

    /**
     * @return Username.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Override toString method.
     *
     * @return Token.
     */
    @Override
    public String toString() {
        return this.token;
    }

}
