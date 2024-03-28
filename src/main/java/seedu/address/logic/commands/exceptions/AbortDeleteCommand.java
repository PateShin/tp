package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class AbortDeleteCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Delete aborted.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLastMentionedPerson(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
