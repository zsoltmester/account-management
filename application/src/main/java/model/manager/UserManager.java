package model.manager;

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
        // TODO use the database for this.
        return true;
    }
}
