package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_CONFIRMATION_COMMAND;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AbortDeleteCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Parses user inputs after confirmation message is shown to user.
 */
public class ConfirmationStageParser {
    private static final Logger logger = LogsCenter.getLogger(PayBackParser.class);

    /**
     * Parses user input to either return DeleteCommand or AbortDeleteCommand.
     */
    public Command parseCommand(Model model, String userInput) throws ParseException {
        switch(userInput.trim().toLowerCase()) {
        case "y":
            return new DeleteCommand();
        case "n":
            return new AbortDeleteCommand();
        default:
            Person personToDelete = model.getLastMentionedPerson();
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_CONFIRMATION_COMMAND, Messages.format(personToDelete))
            );
        }
    }
}
