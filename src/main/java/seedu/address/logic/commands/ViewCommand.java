package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * Command that displays all transactions for the specified employee.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "/view";

    public static final String FOLLOW_MESSAGE = "Follows:\nID";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the transaction of the person identified by the id number provided.\n"
            + "Parameters: ID (must be a positive 6-digit number)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewed Person: %1$s";

    private final Id id;

    private final Predicate<Transaction> predicate;

    /**
     * Constructs a ViewCommand with specified Id.
     */
    public ViewCommand(Id id) {
        this.id = id;
        predicate = transaction -> transaction.getEmployeeId().equals(id);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToView = lastShownList
                .stream()
                .filter(person -> person.getId().value == (this.id.value))
                .findFirst()
                .orElse(null);

        if (personToView == null) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_ID);
        }

        model.updateFilteredTransactionList(predicate);

        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, Messages.format(personToView)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return id.equals(otherViewCommand.id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", id)
                .toString();
    }
}
