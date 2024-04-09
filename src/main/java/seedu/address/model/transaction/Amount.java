package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.NumberFormat;

/**
 * Represents a Transaction's amount in the transaction list.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS = "Amount should be a positive number with at most 2 decimal places "
            + "and should not exceed 9,999,999,999,999.99.";
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?";
    public static final double MAX_AMOUNT = 9_999_999_999_999.99;

    public final double value;

    /**
     * Constructs an {@code Amount}.
     *
     * @param amount A valid amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        value = Double.parseDouble(amount);
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            double amount = Double.parseDouble(test);
            return amount > 0 && amount <= MAX_AMOUNT;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return NUMBER_FORMAT.format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) other;
        return value == otherAmount.value;
    }
}
