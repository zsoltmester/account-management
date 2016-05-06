package model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The account entity.
 */
public class Account {

    private long id;

    private String number;

    private BigDecimal balance;

    private Date creationDate;

    private boolean isActive;

    private Set<Transaction> transactions;

    /**
     * Creates a new account based on the given params.
     *
     * @param id           The ID of the account.
     * @param number       The number of the account.
     * @param balance      The account balance.
     * @param creationDate The account creation date.
     * @param isActive     The account is active or not.
     * @param transactions The account related transactions.
     */
    public Account(long id, String number, BigDecimal balance, Date creationDate, boolean isActive,
                   Set<Transaction> transactions) {
        this.id = id;
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
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
     * Returns the number of this account.
     *
     * @return The numbre of this account.
     */
    public String getNumber() {
        return number;
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
     * Returns the creation date of this account.
     *
     * @return The creation date of this account.
     */
    public Date getCreationDate() {
        return creationDate;
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
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) return true;
        if (rhs == null || getClass() != rhs.getClass()) return false;

        Account account = (Account) rhs;

        return id == account.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
