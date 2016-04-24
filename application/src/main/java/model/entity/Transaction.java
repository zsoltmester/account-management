package model.entity;

import java.math.BigDecimal;

/**
 * The transaction entity.
 */
public class Transaction {

    private long id;

    private String sourceAccount;

    private String targetAccount;

    private BigDecimal amount;

    /**
     * Creates transaction based on the given params.
     *
     * @param id            The ID of this transaction.
     * @param sourceAccount The source account ID.
     * @param targetAccount The target account ID.
     * @param amount        The amount.
     */
    public Transaction(long id, String sourceAccount, String targetAccount, BigDecimal amount) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }

    /**
     * Returns the ID of this transaction.
     *
     * @return The ID of this transaction.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the source account ID.
     *
     * @return The source account ID.
     */
    public String getSourceAccount() {
        return sourceAccount;
    }

    /**
     * Returns the target account ID.
     *
     * @return The target account ID.
     */
    public String getTargetAccount() {
        return targetAccount;
    }

    /**
     * Returns the amount.
     *
     * @return The amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }
}
