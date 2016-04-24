package model.manager;

import java.util.Random;

// TODO add unit test after database integration
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
     * @return <code>true</code>, if the action performed, otherwise <code>false</code>.
     */
    public static boolean activateAccount(long id) {
        return changeStatus(true, id);
    }

    /**
     * Deactivate the given account.
     *
     * @param id The ID of the account to deactivate.
     * @return <code>true</code>, if the action performed, otherwise <code>false</code>.
     */
    public static boolean deactivateAccount(long id) {
        return changeStatus(false, id);
    }

    private static boolean changeStatus(boolean activate, long id) {
        // TODO logic requires database
        return new Random().nextInt() % 2 == 0;
    }
}
