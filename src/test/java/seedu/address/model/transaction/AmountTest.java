package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amounts
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only
        assertFalse(Amount.isValidAmount("abc")); // non-numeric
        assertFalse(Amount.isValidAmount("123.45.6")); // more than 1 decimal point
        assertFalse(Amount.isValidAmount("12345.678")); // more than 2 decimal places
        assertFalse(Amount.isValidAmount("-123")); // negative number
        assertFalse(Amount.isValidAmount("0")); // zero
        assertFalse(Amount.isValidAmount("10000000000000")); // greater than MAX_AMOUNT

        // valid amounts
        assertTrue(Amount.isValidAmount("123")); // integer
        assertTrue(Amount.isValidAmount("123.4")); // 1 decimal place
        assertTrue(Amount.isValidAmount("123.45")); // 2 decimal places
        assertTrue(Amount.isValidAmount("9999999999999.99")); // MAX_AMOUNT
    }

    @Test
    public void equals() {
        Amount amount = new Amount("123.45");

        // same values -> returns true
        assertTrue(amount.equals(new Amount("123.45")));

        // same object -> returns true
        assertTrue(amount.equals(amount));

        // null -> returns false
        assertFalse(amount.equals(null));

        // different types -> returns false
        assertFalse(amount.equals(5.0f));

        // different values -> returns false
        assertFalse(amount.equals(new Amount("123.46")));
    }
}
