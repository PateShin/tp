package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

/**
 * Adds a transaction to PayBack.
 */
public class TransactionCommand extends Command {

    public static final String COMMAND_WORD = "/transaction";

    public static final String FOLLOW_MESSAGE = "Follows\nID; AMOUNT; DESCRIPTION; [DATETIME]";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to PayBack.\n"
            + "Format:\n"
            + "• " + COMMAND_WORD + " ID; AMOUNT; DESCRIPTION; [DATETIME]\n"
            + "• " + COMMAND_WORD + " "
            + PREFIX_ID + " ID "
            + PREFIX_AMOUNT + " AMOUNT "
            + PREFIX_DESCRIPTION + " DESCRIPTION "
            + "[" + PREFIX_DATETIME + " DATETIME]\n"
            + "Examples:\n"
            + "• " + COMMAND_WORD + " 240001; 4500; Salary; 14/05/2024 12:00\n"
            + "• " + COMMAND_WORD + " "
            + PREFIX_ID + " 240001 "
            + PREFIX_AMOUNT + " 4500 "
            + PREFIX_DESCRIPTION + " Salary "
            + PREFIX_DATETIME + " 14/05/2024 12:00";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists in PayBack";

    private final Transaction toAdd;

    /**
     * Creates a TransactionCommand to add the specified {@code Transaction}
     */
    public TransactionCommand(Transaction transaction) {
        requireNonNull(transaction);
        toAdd = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTransaction(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRANSACTION);
        }

        model.addTransaction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TransactionCommand)) {
            return false;
        }

        TransactionCommand otherTransactionCommand = (TransactionCommand) other;
        return toAdd.equals(otherTransactionCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
