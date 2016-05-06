package model.manager;

import resource.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        try {
            String sql = "select " + Configs.USER_TABLE_USER_FIELD + " from " + Configs.USER_TABLE + " where "
                    + Configs.USER_TABLE_USER_FIELD + "='" + user + "' and " +
                    Configs.USER_TABLE_PASSWORD_FIELD + "='" + (password == null ? "null" : new String(password)) + "'";
            ResultSet results = executeSelect(sql);
            boolean ret = results.next();
            results.close();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
