package model;

/**
 * Contains account related actions.
 */
public class AccountManager {

    private AccountManager() {
        throw new UnsupportedOperationException("You cannot instantiate a manager class.");
    }

    /**
     * Activate the given account.
     *
     * @param id The ID of the account to activate.
     * @return <code>true</code>, if the action performed, otherwise <code>false</code>.
     */
    public static boolean activateAccount(long id) {
        // TODO use the database for this
        return true;
    }

    /**
     * Deactivate the given account.
     *
     * @param id The ID of the account to deactivate.
     * @return <code>true</code>, if the action performed, otherwise <code>false</code>.
     */
    public static boolean deactivateAccount(long id) {
        // TODO use the database for this
        return true;
    }
}
