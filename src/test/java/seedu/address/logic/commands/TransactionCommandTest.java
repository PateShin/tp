package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYEEID;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PayBack;
import seedu.address.model.UserPrefs;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TransactionBuilder;

public class TransactionCommandTest {

    private final Model model = new ModelManager(new PayBack(), new UserPrefs());
    private TransactionCommand transactionCommand;

    @BeforeEach
    public void setUp() {
        // Set up the model manager
        model.addPerson(new PersonBuilder(AMY).withId(VALID_EMPLOYEEID).build());

        // Set up the transaction command
        transactionCommand = new TransactionCommand(TRANSACTION_4);
    }

    @Test
    public void execute_validTransaction_success() throws CommandException {
        Model expectedModel = new ModelManager(model.getPayBack(), new UserPrefs());
        expectedModel.addTransaction(TRANSACTION_4);

        assertCommandSuccess(transactionCommand, model,
                String.format(TransactionCommand.MESSAGE_SUCCESS, Messages.format(TRANSACTION_4)), expectedModel);
    }

    @Test
    public void execute_duplicateTransaction_throwsCommandException() {
        // Add the transaction to the model first
        model.addTransaction(TRANSACTION_4);

        // Try to add the same transaction again
        assertCommandFailure(transactionCommand, model, TransactionCommand.MESSAGE_DUPLICATE_TRANSACTION);
    }

    @Test
    public void equals() {
        Transaction transaction = new TransactionBuilder().build();
        TransactionCommand transactionCommand1 = new TransactionCommand(transaction);
        TransactionCommand transactionCommand2 = new TransactionCommand(transaction);
        assertEquals(transactionCommand1, transactionCommand2);
        assertNotEquals(1, transactionCommand1);
        assertNotEquals(null, transactionCommand1);
        assertNotEquals(transactionCommand1, new TransactionCommand(TRANSACTION_4));
    }

    @Test
    public void toStringMethod() {
        Transaction transaction = new TransactionBuilder().build();
        TransactionCommand transactionCommand = new TransactionCommand(transaction);
        String expected = TransactionCommand.class.getCanonicalName() + "{toAdd=" + transaction + "}";
        assertEquals(expected, transactionCommand.toString());
    }
}
