package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Aborts the deletion of command.
 */
public class AbortDeleteCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Deletion aborted.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLastMentionedPerson(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
