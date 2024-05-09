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

    public static String hashPassword(String password) {
        // Hash the password using BCrypt
        var result = BCrypt.hashpw(password, BCrypt.gensalt());
        return result;
    }

    public static Boolean compareHash(String inputPw, String hashToCompare) {
        return BCrypt.checkpw(inputPw, hashToCompare);
    }
}
