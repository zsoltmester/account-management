package model.manager;

import model.entity.Account;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the {@link AccountManager} class.
 */
public class AccountManagerTest {

    @BeforeClass
    public static void connectDatabase() throws SQLException {
        DatabaseManager.connect();
    }

    @Test
    public void changeStatus() {
        Account account = CustomerManager.getCustomer(1).getAccounts().iterator().next();
        if (account.isActive()) {
            AccountManager.deactivateAccount(account.getId());
            account = CustomerManager.getCustomer(1).getAccounts().iterator().next();
            assertFalse(account.isActive());
        } else {
            AccountManager.activateAccount(account.getId());
            account = CustomerManager.getCustomer(1).getAccounts().iterator().next();
            assertTrue(account.isActive());
        }
    }
}