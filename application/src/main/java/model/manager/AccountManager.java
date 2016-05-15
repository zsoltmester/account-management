package model.manager;

import resource.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains account related actions.
 */
public class AccountManager extends DatabaseManager {

    private AccountManager() {
        super();
    }

    /**
     * Activate the given account.
     *
     * @param id The ID of the account to activate.
     * @return If the activation was successful or not.
     */
    public static boolean activateAccount(long id) {
        return changeStatus(true, id);
    }

    /**
     * Deactivate the given account.
     *
     * @param id The ID of the account to deactivate.
     * @return If the deactivation was successful or not.
     */
    public static boolean deactivateAccount(long id) {
        return changeStatus(false, id);
    }

    private static boolean changeStatus(boolean activate, long id) {
        String sql = "update " + Configs.ACCOUNT_TABLE + " set " + Configs.ACCOUNT_TABLE_STATUS_FIELD + " = "
                + (activate ? "0" : "1") + " where " + Configs.ACCOUNT_TABLE_ID_FIELD + " = " + id;
        return executeUpdate(sql);
    }

    /**
     * Returns the account ID for the given account number.
     *
     * @param number The account number to query.
     * @return The account ID for the given account number.
     */
    public static Long getIdForNumber(String number) {
        String sql = "select " + Configs.ACCOUNT_TABLE_ID_FIELD + " from " + Configs.ACCOUNT_TABLE + " where "
                + Configs.ACCOUNT_TABLE_ACCOUNT_NUMBER_FIELD + " = '" + number + '\'';
        ResultSet resultSet = executeSelect(sql);
        if (resultSet == null) {
            return null;
        } else {
            try {
                resultSet.next();
                return resultSet.getLong(1);
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
