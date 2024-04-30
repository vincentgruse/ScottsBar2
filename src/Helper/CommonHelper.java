package src.Helper;

import org.mindrot.jbcrypt.BCrypt;

public class CommonHelper {
    public static String hashPassword(String password) {
        // Hash the password using BCrypt
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
