package seedu.address.model.transaction;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Represents a Transaction's ID in the transaction list.
 * Guarantees: immutable;
 */
public class TransactionId {

    public static final String MESSAGE_CONSTRAINTS =
            "ID should only contain numbers "
                    + "and it must be the unix timestamp between 1/1/2010 to the current datetime.";

    private static final long MIN_ID = LocalDateTime.of(2010, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);

    public final long value;

    /**
     * Constructs a {@code Id}.
     */
    public TransactionId() {
        value = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid ID.
     */
    public TransactionId(long id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given long is a valid ID.
     */
    public static boolean isValidId(long test) {
        return test >= MIN_ID && test <= LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
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
