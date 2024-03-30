package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TransactionBuilder;

public class TransactionTest {

    @Test
    public void isSameTransaction() {
        // same object -> returns true
        assertTrue(TRANSACTION_1.isSameTransaction(TRANSACTION_1));

        // null -> returns false
        assertFalse(TRANSACTION_1.isSameTransaction(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Transaction transaction1Copy = new TransactionBuilder(TRANSACTION_1).build();
        assertTrue(TRANSACTION_1.equals(transaction1Copy));

        // same object -> returns true
        assertTrue(TRANSACTION_1.equals(TRANSACTION_1));

        // null -> returns false
        assertFalse(TRANSACTION_1.equals(null));

        // different type -> returns false
        assertFalse(TRANSACTION_1.equals(5));

        // different transaction -> returns false
        assertFalse(TRANSACTION_1.equals(new TransactionBuilder().withAmount("100").build()));
    }

    @Test
    public void toStringMethod() {
        String expected = Transaction.class.getCanonicalName() + "{id=" + TRANSACTION_1.getId()
                + ", dateTime=" + TRANSACTION_1.getDateTime() + ", employeeId=" + TRANSACTION_1.getEmployeeId()
                + ", amount=" + TRANSACTION_1.getAmount() + ", description=" + TRANSACTION_1.getDescription() + "}";
        assertTrue(TRANSACTION_1.toString().equals(expected));
    }
}
