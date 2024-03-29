package seedu.address.model.transaction;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Represents a Transaction's ID in the transaction list.
 * Guarantees: immutable;
 */
public class TransactionId {

    public final long value;

    /**
     * Constructs a {@code Id}.
     */
    public TransactionId() {
        value = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionId)) {
            return false;
        }

        TransactionId otherId = (TransactionId) other;
        return value == otherId.value;
    }
}
