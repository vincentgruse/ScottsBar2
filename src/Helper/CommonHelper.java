package Helper;

import org.mindrot.jbcrypt.BCrypt;

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
