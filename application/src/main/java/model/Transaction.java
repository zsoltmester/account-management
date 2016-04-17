package model;

import java.math.BigDecimal;

/**
 * The transaction entity.
 */
public class Transaction {

    private long sourceAccount;
    private long targetAccount;

    private BigDecimal amount;

    /**
     * Creates transaction based on the given params.
     *
     * @param sourceAccount The source account ID.
     * @param targetAccount The target account ID.
     * @param amount The amount.
     */
    public Transaction(long sourceAccount, long targetAccount, BigDecimal amount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }

    /**
     * Returns the source account ID.
     *
     * @return The source account ID.
     */
    public long getSourceAccount() {
        return sourceAccount;
    }

    /**
     * Returns the target account ID.
     *
     * @return The target account ID.
     */
    public long getTargetAccount() {
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
