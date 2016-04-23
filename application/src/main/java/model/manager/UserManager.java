package model.manager;

import java.util.Base64;

/**
 * Contains user related actions.
 */
public class UserManager extends DatabaseManager {

    private UserManager() {
        super();
    }

    /**
     * Validates the given credentials.
     *
     * @param user     The user.
     * @param password The password in base 64 encoding.
     * @return <code>true</code> if the credentials are valid, otherwise <code>false</code>.
     */
    public static boolean isValid(String user, byte[] password) {
        // TODO just a temp logic until we have database
        return "admin".equals(user) && "admin".equals(new String(Base64.getDecoder().decode(password)));
    }
}
