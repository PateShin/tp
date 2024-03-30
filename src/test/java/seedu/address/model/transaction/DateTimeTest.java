package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null datetime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid datetimes
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("abc"));
        assertFalse(DateTime.isValidDateTime("2020-02-30 12:00")); // invalid date format// invalid time

        // valid datetimes
        assertTrue(DateTime.isValidDateTime("29/02/2022 23:59"));
        assertTrue(DateTime.isValidDateTime("01/01/2022 00:00"));
    }

    @Test
    public void equals() {
        DateTime dateTime = new DateTime("01/01/2022 00:00");

        // same values -> returns true
        assertTrue(dateTime.equals(new DateTime("01/01/2022 00:00")));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different types -> returns false
        assertFalse(dateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(dateTime.equals(new DateTime("01/01/2022 00:01")));
    }
}
