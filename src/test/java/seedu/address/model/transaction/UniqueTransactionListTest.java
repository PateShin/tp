package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.exceptions.DuplicateTransactionException;
import seedu.address.testutil.TransactionBuilder;

public class UniqueTransactionListTest {

    private final UniqueTransactionList uniqueTransactionList = new UniqueTransactionList();

    @Test
    public void contains_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.contains(null));
    }

    @Test
    public void contains_transactionNotInList_returnsFalse() {
        assertFalse(uniqueTransactionList.contains(TRANSACTION_1));
    }

    @Test
    public void contains_transactionInList_returnsTrue() {
        uniqueTransactionList.add(TRANSACTION_1);
        assertTrue(uniqueTransactionList.contains(TRANSACTION_1));
    }

    @Test
    public void contains_transactionWithSameFieldsDifferentIdInList_returnsTrue() {
        uniqueTransactionList.add(TRANSACTION_1);
        Transaction editedTransaction = new TransactionBuilder(TRANSACTION_1).build();
        assertTrue(uniqueTransactionList.contains(editedTransaction));
    }

    @Test
    public void add_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.add(null));
    }

    @Test
    public void add_duplicateTransaction_throwsDuplicateTransactionException() {
        uniqueTransactionList.add(TRANSACTION_1);
        assertThrows(DuplicateTransactionException.class, () -> uniqueTransactionList.add(TRANSACTION_1));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTransactionList.asUnmodifiableObservableList()
                .remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTransactionList.asUnmodifiableObservableList().toString(), uniqueTransactionList.toString());
    }
}
