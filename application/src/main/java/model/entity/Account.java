package model.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * The account entity.
 */
public class Account {

    private long id;

    private BigDecimal balance;

    private boolean isActive;

    private List<Transaction> transactions;

    /**
     * Creates a new account based on the given params.
     *
     * @param id           The ID of the account.
     * @param balance      The account balance.
     * @param isActive     The account is active or not.
     * @param transactions The account related transactions.
     */
    public Account(long id, BigDecimal balance, boolean isActive, List<Transaction> transactions) {
        this.id = id;
        this.balance = balance;
        this.isActive = isActive;
        this.transactions = transactions;
    }

    /**
     * Returns the ID of this account.
     *
     * @return The ID of this account.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the balance of this account.
     *
     * @return The balance of this account.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Returns if this account is active or not.
     *
     * @return <code>true</code>, if this account is active, otherwise <code>false</code>.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the transactions related to this account.
     *
     * @return The transactions related to this account.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
