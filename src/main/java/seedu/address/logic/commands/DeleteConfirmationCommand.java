package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteConfirmationCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Are you sure you want to delete the following person?:\n"
            + " %1$s"
            + "(Y/N)";

    private final Id id;

    /**
     * Creates a DeleteCommand to delete the worker by specific ID.
     *
     * @param id ID of the worker to be deleted.
     */
    public DeleteConfirmationCommand(Id id) {
        requireNonNull(id);
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = lastShownList
                .stream()
                .filter(person -> person.getId().value == (this.id.value))
                .findFirst()
                .orElse(null);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID);
        }

        model.setLastMentionedPerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteConfirmationCommand)) {
            return false;
        }

        DeleteConfirmationCommand otherDeleteCommand = (DeleteConfirmationCommand) other;
        return id.equals(otherDeleteCommand.id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", id)
                .toString();
    }
}
