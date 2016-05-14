package model.manager;

import model.entity.Account;
import model.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cases for the {@link TransactionManager} class.
 */
public class TransactionManagerTest {

    @BeforeClass
    public static void connectDatabase() throws SQLException {
        DatabaseManager.connect();
    }

    @Test
    public void performTransaction() throws SQLException {
        boolean thrown = false;
        assertFalse(TransactionManager.performTransaction(0, 0, null));
        assertFalse(TransactionManager.performTransaction(1, 2, new BigDecimal(-1)));
        assertFalse(TransactionManager.performTransaction(1, 2, new BigDecimal(100000)));
        assertFalse(TransactionManager.performTransaction(1, 1, new BigDecimal(1)));

        Customer customer = CustomerManager.getCustomer(2);
        List<Account> accounts = new ArrayList<>(customer.getAccounts());
        assertTrue(accounts.size() >= 2);
        Account aOldAccount = accounts.get(0);
        Account bOldAccount = accounts.get(1);
        BigDecimal amount = new BigDecimal(2);
        assertTrue(TransactionManager.performTransaction(aOldAccount.getId(), bOldAccount.getId(), amount));

        customer = CustomerManager.getCustomer(2);
        long aOldAccountId = aOldAccount.getId();
        Account aNewAccount = customer
                .getAccounts()
                .stream()
                .filter(account -> account.getId() == aOldAccountId)
                .findAny()
                .get();
        long bOldAccountId = bOldAccount.getId();
        Account bNewAccount = customer
                .getAccounts()
                .stream()
                .filter(account -> account.getId() == bOldAccountId)
                .findAny()
                .get();
        assertEquals(aOldAccount.getTransactions().size() + 1, aNewAccount.getTransactions().size());
        assertEquals(bOldAccount.getTransactions().size() + 1, bNewAccount.getTransactions().size());
        assertEquals(bOldAccount.getBalance().add(amount), bNewAccount.getBalance());
        assertEquals(aOldAccount.getBalance().subtract(amount.multiply(new BigDecimal(1.05))).setScale(3, BigDecimal.ROUND_UP),
                aNewAccount.getBalance());

        BigDecimal validAmount = new BigDecimal(3);
        BigDecimal invalidAmount = new BigDecimal(0);
        assertFalse(TransactionManager.performTransaction(
                Arrays.asList(aNewAccount.getId(), aNewAccount.getId()),
                bNewAccount.getId(),
                Arrays.asList(validAmount, invalidAmount)));
        aOldAccount = aNewAccount;
        bOldAccount = bNewAccount;
        customer = CustomerManager.getCustomer(2);
        long aOldAccountIdAfterBatch = aOldAccount.getId();
        aNewAccount = customer
                .getAccounts()
                .stream()
                .filter(account -> account.getId() == aOldAccountIdAfterBatch)
                .findAny()
                .get();
        long bOldAccountIdAfterBatch = bOldAccount.getId();
        bNewAccount = customer
                .getAccounts()
                .stream()
                .filter(account -> account.getId() == bOldAccountIdAfterBatch)
                .findAny()
                .get();
        assertEquals(aOldAccount.getTransactions().size(), aNewAccount.getTransactions().size());
        assertEquals(bOldAccount.getTransactions().size(), bNewAccount.getTransactions().size());
    }

    @Test
    public void cancelTransaction() throws SQLException {
        // TODO
    }
}