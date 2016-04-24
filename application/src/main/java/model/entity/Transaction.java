package model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The transaction entity.
 */
public class Transaction {

    private long id;

    private String sourceAccount;

    private String targetAccount;

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
    public Transaction(long id, String sourceAccount, String targetAccount, BigDecimal amount, Date creationDate) {
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

    /**
     * Returns the creation date.
     *
     * @return The creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }
}
