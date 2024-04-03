package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.exceptions.DuplicateTransactionException;
import seedu.address.model.transaction.exceptions.TransactionNotFoundException;
import seedu.address.testutil.TransactionBuilder;
import seedu.address.testutil.TypicalTransactions;

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

    @Test
    public void remove_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> uniqueTransactionList.remove(TRANSACTION_1));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTransactionList.add(TRANSACTION_1);
        uniqueTransactionList.remove(TRANSACTION_1);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransactions_uniqueTransactionList_replacesOwnListWithProvidedUniqueTransactionList() {
        UniqueTransactionList originalList = new UniqueTransactionList();
        originalList.add(TypicalTransactions.TRANSACTION_1);
        UniqueTransactionList replacementList = new UniqueTransactionList();
        replacementList.add(TypicalTransactions.TRANSACTION_2);
        originalList.setTransactions(replacementList);
        assertEquals(replacementList, originalList);
    }

    @Test
    public void setTransactions_listWithDuplicateTransactions_throwsDuplicateTransactionException() {
        List<Transaction> listWithDuplicateTransactions = Arrays.asList(TRANSACTION_1, TRANSACTION_1);
        assertThrows(DuplicateTransactionException.class, () ->
                uniqueTransactionList.setTransactions(listWithDuplicateTransactions));
    }

    @Test
    public void setTransactions_listWithUniqueTransactions_replacesOwnListWithProvidedList() {
        List<Transaction> uniqueTransactions = Arrays.asList(TypicalTransactions.TRANSACTION_1,
                TypicalTransactions.TRANSACTION_2);
        uniqueTransactionList.setTransactions(uniqueTransactions);
        assertEquals(uniqueTransactions, uniqueTransactionList.asUnmodifiableObservableList());
    }

    @Test
    public void iterator_returnsIteratorOfInternalList() {
        // Add transactions to the list
        uniqueTransactionList.add(TypicalTransactions.TRANSACTION_1);
        uniqueTransactionList.add(TypicalTransactions.TRANSACTION_2);

        // Get the iterator
        Iterator<Transaction> iterator = uniqueTransactionList.iterator();

        // Verify that the iterator returns the transactions in the expected order
        assertTrue(iterator.hasNext());
        assertEquals(TRANSACTION_1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TypicalTransactions.TRANSACTION_2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void equals_otherUniqueTransactionListWithSameTransactions_returnsTrue() {
        UniqueTransactionList otherList = new UniqueTransactionList();
        otherList.add(TRANSACTION_1);
        otherList.add(TypicalTransactions.TRANSACTION_2);

        uniqueTransactionList.add(TRANSACTION_1);
        uniqueTransactionList.add(TypicalTransactions.TRANSACTION_2);

        assertEquals(uniqueTransactionList, otherList);
    }

    @Test
    public void equals_otherUniqueTransactionListWithDifferentTransactions_returnsFalse() {
        UniqueTransactionList otherList = new UniqueTransactionList();
        otherList.add(TRANSACTION_1);

        uniqueTransactionList.add(TypicalTransactions.TRANSACTION_2);

        assertNotEquals(uniqueTransactionList, otherList);
    }

    @Test
    public void equals_null_returnsFalse() {
        assertNotEquals(null, uniqueTransactionList);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertNotEquals("string", uniqueTransactionList);
    }

    @Test
    public void hashCode_uniqueTransactionListsWithSameTransactions_returnsSameHashCode() {
        UniqueTransactionList otherList = new UniqueTransactionList();
        otherList.add(TRANSACTION_1);
        otherList.add(TypicalTransactions.TRANSACTION_2);

        uniqueTransactionList.add(TRANSACTION_1);
        uniqueTransactionList.add(TypicalTransactions.TRANSACTION_2);

        assertEquals(uniqueTransactionList.hashCode(), otherList.hashCode());
    }
}
