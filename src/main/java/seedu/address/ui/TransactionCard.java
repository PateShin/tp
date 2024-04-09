package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.transaction.Transaction;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TransactionCard extends UiPart<Region> {

    private static final String FXML = "TransactionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Transaction transaction;

    @FXML
    private Label dateTime;
    @FXML
    private Label description;
    @FXML
    private Label transactionId;
    @FXML
    private Label amount;
    @FXML
    private Label employeeId;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TransactionCard(Transaction transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;

        String transactionLabel = "Transaction ID: ";
        String transactionIdText = transaction.getId().toString();
        transactionId.setText(transactionLabel + transactionIdText);

        String amountLabel = "Amount: ";
        String amountText = transaction.getAmount().toString();
        amount.setText(amountLabel + amountText);

        String employeeIdLabel = "Employee ID: ";
        String employeeIdText = transaction.getEmployeeId().toString();
        employeeId.setText(employeeIdLabel + employeeIdText);

        String descriptionLabel = "Description: ";
        String descriptionText = transaction.getDescription().toString();
        description.setText(descriptionLabel + descriptionText);


        String dateLabel = "DateTime: ";
        String dateText = transaction.getDateTime().toString();
        dateTime.setText(dateLabel + dateText);
    }
}
