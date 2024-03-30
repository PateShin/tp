package seedu.address.testutil;

import seedu.address.model.person.Id;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionId;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final long DEFAULT_ID = 1711815646;
    public static final String DEFAULT_DATETIME = "30/03/2024 16:20";
    public static final int DEFAULT_EMPLOYEEID = 240001;
    public static final String DEFAULT_AMOUNT = "5000";
    public static final String DEFAULT_DESCRIPTION = "Salary";

    private TransactionId id;
    private DateTime dateTime;
    private Id employeeId;
    private Amount amount;
    private Description description;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        id = new TransactionId();
        dateTime = new DateTime(DEFAULT_DATETIME);
        employeeId = new Id(DEFAULT_EMPLOYEEID);
        amount = new Amount(DEFAULT_AMOUNT);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code transactionToCopy}.
     */
    public TransactionBuilder(Transaction transactionToCopy) {
        id = transactionToCopy.getId();
        dateTime = transactionToCopy.getDateTime();
        employeeId = transactionToCopy.getEmployeeId();
        amount = transactionToCopy.getAmount();
        description = transactionToCopy.getDescription();
    }

    /**
     * Sets the id of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withId(long id) {
        this.id = new TransactionId(id);
        return this;
    }

    /**
     * Sets the dateTime of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Sets the employeeId of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withEmployeeId(int employeeId) {
        this.employeeId = new Id(employeeId);
        return this;
    }

    /**
     * Sets the amount of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the description of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Transaction build() {
        return new Transaction(id, dateTime, employeeId, amount, description);
    }
}
