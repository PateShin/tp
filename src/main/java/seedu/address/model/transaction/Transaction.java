package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Id;


/**
 * Represents a Transaction in the transaction list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {

    private final TransactionId id;
    private final DateTime dateTime;
    private final Id employeeId;
    private final Amount amount;
    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public Transaction(DateTime dateTime, Id employeeId, Amount amount, Description description) {
        requireAllNonNull(employeeId, dateTime, amount, description);
        this.id = new TransactionId();
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Every field must be present and not null.
     */
    public Transaction(TransactionId id, DateTime dateTime, Id employeeId, Amount amount, Description description) {
        requireAllNonNull(id, dateTime, employeeId, amount, description);
        this.id = id;
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
    }

    public TransactionId getId() {
        return id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Id getEmployeeId() {
        return employeeId;
    }

    public Amount getAmount() {
        return amount;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both transactions have the same ID.
     * This defines a weaker notion of equality between two transactions.
     */
    public boolean isSameTransaction(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction != null
                && otherTransaction.getId().equals(getId());
    }

    /**
     * Returns true if both transactions have the same fields.
     * This defines a stronger notion of equality between two transactions.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getId().equals(getId())
                && otherTransaction.getDateTime().equals(getDateTime())
                && otherTransaction.getEmployeeId().equals(getEmployeeId())
                && otherTransaction.getAmount().equals(getAmount())
                && otherTransaction.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, dateTime, employeeId, amount, description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("dateTime", dateTime)
                .add("employeeId", employeeId)
                .add("amount", amount)
                .add("description", description)
                .toString();
    }
}
