package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "/delete";

    public static final String FOLLOW_MESSAGE = "Follows:\nID";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the id number provided.\n"
            + "Parameters: ID (must be a positive 6-digit number)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDelete = model.getLastMentionedPerson();
        assert(personToDelete != null);

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof DeleteCommand);
    }
}
