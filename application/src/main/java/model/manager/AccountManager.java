package model.manager;

import resource.Configs;

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
     */
    public static void activateAccount(long id) {
        changeStatus(true, id);
    }

    /**
     * Deactivate the given account.
     *
     * @param id The ID of the account to deactivate.
     */
    public static void deactivateAccount(long id) {
        changeStatus(false, id);
    }

    private static void changeStatus(boolean activate, long id) {
        String sql = "update " + Configs.ACCOUNT_TABLE + " set " + Configs.ACCOUNT_TABLE_STATUS_FIELD + " = "
                + (activate ? "0" : "1") + " where " + Configs.ACCOUNT_TABLE_ID_FIELD + " = " + id;
        try {
            executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
