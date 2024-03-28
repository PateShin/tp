package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.AbortDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConfirmationStageParser{
    private static final Logger logger = LogsCenter.getLogger(PayBackParser.class);

    public Command parseCommand(String userInput) throws ParseException{
        switch(userInput.toLowerCase()) {
        case "y":
            return new DeleteCommand();
        case "n":
            return new AbortDeleteCommand();
        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
