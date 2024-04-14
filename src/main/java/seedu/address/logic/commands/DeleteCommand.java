package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "/delete";

    public static final String FOLLOW_MESSAGE = "Format: " + COMMAND_WORD + " ID";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified employee from the PayBack system.\n"
            + "Format: " + COMMAND_WORD + " ID (between 100001 and 999999)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDelete = model.getLastMentionedPerson();
        assert(personToDelete != null);

        model.deletePerson(personToDelete);
        Id id = personToDelete.getId();

        model.updateFilteredTransactionList(transaction -> transaction.getEmployeeId().equals(id));
        List<Transaction> transactionsToDelete = model.getFilteredTransactionList();

        for (int i = 0; i < transactionsToDelete.size(); i++) {
            model.deleteTransaction(transactionsToDelete.get(i));
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof DeleteCommand);
    }
}
