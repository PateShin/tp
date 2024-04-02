package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;


public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "/view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the transaction of the person identified by the id number provided.\n"
            + "Parameters: ID (must be a positive 6-digit number)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewed Person: %1$s";

    private final Id id;

    private final Predicate<Transaction> predicate;
    public ViewCommand(Id id, Predicate<Transaction> predicate) {
        this.id = id;
        this.predicate = predicate;
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
}
