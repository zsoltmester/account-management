package model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The transaction entity.
 */
public class Transaction {

    private long id;

    private long sourceAccount;

    private long targetAccount;

    private BigDecimal amount;

    private Date creationDate;

    /**
     * Creates transaction based on the given params.
     *
     * @param id            The ID of this transaction.
     * @param sourceAccount The source account ID.
     * @param targetAccount The target account ID.
     * @param amount        The amount.
     * @param creationDate  The creation date.
     */
    public Transaction(long id, long sourceAccount, long targetAccount, BigDecimal amount, Date creationDate) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.creationDate = creationDate;
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

    /**
     * Returns the creation date.
     *
     * @return The creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) return true;
        if (rhs == null || getClass() != rhs.getClass()) return false;

        Transaction transaction = (Transaction) rhs;

        return id == transaction.getId();

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
