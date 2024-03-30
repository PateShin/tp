package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTransaction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Id;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Description;

public class JsonAdaptedTransactionTest {
    private static final long INVALID_ID = 123;
    private static final String INVALID_DATETIME = "01-01-2024 12:00";
    private static final int INVALID_EMPLOYEEID = 123;
    private static final double INVALID_AMOUNT = -1.0;

    private static final long VALID_ID = TRANSACTION_1.getId().value;
    private static final String VALID_DATETIME = TRANSACTION_1.getDateTime().toString();
    private static final int VALID_EMPLOYEEID = TRANSACTION_1.getEmployeeId().value;
    private static final double VALID_AMOUNT = TRANSACTION_1.getAmount().value;
    private static final String VALID_DESCRIPTION = TRANSACTION_1.getDescription().toString();

    @Test
    public void toModelType_validTransactionDetails_returnsTransaction() throws Exception {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(TRANSACTION_1);
        assertEquals(TRANSACTION_1, transaction.toModelType());
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_ID, INVALID_DATETIME, VALID_EMPLOYEEID,
                        VALID_AMOUNT, VALID_DESCRIPTION);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(VALID_ID, null, VALID_EMPLOYEEID,
                VALID_AMOUNT, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidEmployeeId_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_ID, VALID_DATETIME, INVALID_EMPLOYEEID,
                        VALID_AMOUNT, VALID_DESCRIPTION);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction =
                new JsonAdaptedTransaction(VALID_ID, VALID_DATETIME, VALID_EMPLOYEEID,
                        INVALID_AMOUNT, VALID_DESCRIPTION);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTransaction transaction = new JsonAdaptedTransaction(VALID_ID, VALID_DATETIME, VALID_EMPLOYEEID,
                VALID_AMOUNT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }
}
