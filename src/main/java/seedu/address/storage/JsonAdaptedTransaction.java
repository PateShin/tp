package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Id;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionId;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
public class JsonAdaptedTransaction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Transaction's %s field is missing!";

    private final long id;
    private final String dateTime;
    private final int employeeId;
    private final double amount;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedTransaction(@JsonProperty("id") long id, @JsonProperty("dateTime") String dateTime,
            @JsonProperty("employeeId") int employeeId, @JsonProperty("amount") double amount,
            @JsonProperty("description") String description) {
        this.id = id;
        this.dateTime = dateTime;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Converts a given {@code Transaction} into this class for Jackson use.
     */
    public JsonAdaptedTransaction(Transaction source) {
        id = source.getId().value;
        dateTime = source.getDateTime().toString();
        employeeId = source.getEmployeeId().value;
        amount = source.getAmount().value;
        description = source.getDescription().value;
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted transaction.
     */
    public Transaction toModelType() throws IllegalValueException {
        final TransactionId modelId = new TransactionId(id);

        if (dateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName())
            );
        }
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        final Id modelEmployeeId = new Id(employeeId);

        if (!Amount.isValidAmount(String.valueOf(amount))) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(String.format("%.2f", amount));

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName())
            );
        }
        final Description modelDescription = new Description(description);

        return new Transaction(modelId, modelDateTime, modelEmployeeId, modelAmount, modelDescription);
    }
}
