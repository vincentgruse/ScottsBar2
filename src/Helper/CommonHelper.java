package Helper;

import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CommonHelper {
//    public static String hashPassword(String password, byte[] salt) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(salt);
//            byte[] hashedPassword = md.digest(password.getBytes());
//            return Base64.getEncoder().encodeToString(hashedPassword);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static byte[] generateSalt() {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        return salt;
//    }
//
//    private static final String ALGORITHM = "AES";
//    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
//
//    public static String encryptPassword(String password, String encryptionKey) {
//        try {
//            Key key = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
//            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
//            return Base64.getEncoder().encodeToString(encryptedBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String decryptPassword(String encryptedPassword, String encryptionKey) {
//        try {
//            Key key = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
//            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
//            return new String(decryptedBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static boolean authenticate(String password, String storedHashedPassword, byte[] storedSalt, String encryptionKey) {
//        String hashedPassword = hashPassword(password, storedSalt);
//        String decryptedPassword = decryptPassword(storedHashedPassword, encryptionKey);
//        return hashedPassword.equals(decryptedPassword);
//    }

    public static String hashPassword(String password) {
        // Hash the password using BCrypt
        var result = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Result: "+result);
        System.out.println("Compare: "+BCrypt.checkpw(password, result));
        return result;
    }

    public static Boolean compareHash(String inputPw, String hashToCompare) {
        var result = BCrypt.hashpw(inputPw, BCrypt.gensalt());
        System.out.println("Check Result: "+result);
        return BCrypt.checkpw(inputPw, hashToCompare);
    }
}
