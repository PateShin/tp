package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYEEID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PayBack;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {

    public static final Transaction TRANSACTION_1 = new TransactionBuilder().withId(1711815800)
            .withDateTime("01/01/2024 12:00").withEmployeeId(240001)
            .withAmount("10.00").withDescription("Lunch").build();
    public static final Transaction TRANSACTION_2 = new TransactionBuilder().withId(1711825434)
            .withDateTime("01/01/2024 13:00").withEmployeeId(240002)
            .withAmount("20").withDescription("Claims").build();
    public static final Transaction TRANSACTION_3 = new TransactionBuilder().withId(1711826000).withEmployeeId(240003)
            .withAmount("30.0").withDescription("Transport").build();

    // Manually added - Transaction's details found in {@code CommandTestUtil}
    public static final Transaction TRANSACTION_4 = new TransactionBuilder().withDateTime(VALID_DATETIME)
            .withEmployeeId(VALID_EMPLOYEEID).withAmount(VALID_AMOUNT).withDescription(VALID_DESCRIPTION).build();

    private TypicalTransactions() {} // prevents instantiation

    /**
     * Returns an {@code PayBack} with all the typical transactions.
     */
    public static PayBack getTypicalPayBack() {
        PayBack pb = new PayBack();
        for (Transaction transaction : getTypicalTransactions()) {
            pb.addTransaction(transaction);
        }
        return pb;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(TRANSACTION_1, TRANSACTION_2, TRANSACTION_3));
    }
}
