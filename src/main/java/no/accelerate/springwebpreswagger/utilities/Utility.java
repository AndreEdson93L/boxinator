package no.accelerate.springwebpreswagger.utilities;

import jakarta.servlet.http.HttpSession;
import no.accelerate.springwebpreswagger.models.User;
import no.accelerate.springwebpreswagger.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

public class Utility {

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static Optional<User> getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        return Optional.ofNullable(currentUser);
    }
    //public static User findUserByEmail(String email, UserRepository userRepository) {
    //    return userRepository.findByEmail(email).orElse(null);
    //}

}
