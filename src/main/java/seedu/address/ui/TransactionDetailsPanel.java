package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of persons.
 */
public class TransactionDetailsPanel extends UiPart<Region> {
    private static final String FXML = "TransactionDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransactionDetailsPanel.class);

    @FXML
    private ListView<Transaction> transactionDetailsView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TransactionDetailsPanel(ObservableList<Transaction> personList) {
        super(FXML);
        transactionDetailsView.setItems(personList);
        transactionDetailsView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionCard(transaction, getIndex() + 1).getRoot());
            }
        }
    }

}
