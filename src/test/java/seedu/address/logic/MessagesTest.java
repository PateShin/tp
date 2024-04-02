package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.Transaction;

public class MessagesTest {

    @Test
    public void format_typicalTransaction_returnsCorrectString() {
        Transaction transaction = TRANSACTION_1;
        String expected = "Transaction ID: " + transaction.getId()
                + "; DateTime: " + transaction.getDateTime()
                + "; Employee ID: " + transaction.getEmployeeId()
                + "; Amount: " + transaction.getAmount()
                + "; Description: " + transaction.getDescription();

        assertEquals(expected, Messages.format(transaction));
    }
}
