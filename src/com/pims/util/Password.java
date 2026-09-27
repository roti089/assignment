package com.pims.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Password {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    private Password() {
    }

    private static byte[] generate_hash(
            String password,
            byte[] salt)
            throws Exception {

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
        );

        SecretKeyFactory factory =
                SecretKeyFactory.getInstance(
                        "PBKDF2WithHmacSHA256"
                );

        return factory
                .generateSecret(spec)
                .getEncoded();
    }

    public static String hash_password(String password) {

        try {

            byte[] salt = new byte[SALT_LENGTH];

            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            byte[] hash = generate_hash(
                    password,
                    salt
            );

            return Base64.getEncoder()
                    .encodeToString(salt)
                    + "$"
                    + Base64.getEncoder()
                    .encodeToString(hash);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Could not hash password.",
                    e
            );
        }
    }

    public static boolean verify_password(
            String password,
            String storedPassword) {

        try {

            if (storedPassword == null
                    || !storedPassword.contains("$")) {

                return false;
            }

            String[] parts =
                    storedPassword.split("\\$");

            if (parts.length != 2) {
                return false;
            }

            byte[] salt =
                    Base64.getDecoder()
                            .decode(parts[0]);

            byte[] storedHash =
                    Base64.getDecoder()
                            .decode(parts[1]);

            byte[] calculatedHash =
                    generate_hash(
                            password,
                            salt
                    );

            return MessageDigest.isEqual(
                    storedHash,
                    calculatedHash
            );

        } catch (Exception e) {

            return false;
        }
    }
}