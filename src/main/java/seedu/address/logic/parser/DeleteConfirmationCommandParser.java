package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteConfirmationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;

/**
 * Parses user input and creates a DeleteConfirmationCommand object.
 */
public class DeleteConfirmationCommandParser implements Parser<DeleteConfirmationCommand> {

    @Override
    public DeleteConfirmationCommand parse(String args) throws ParseException, IllegalArgumentException {
        try {
            Id id = ParserUtil.parseId(args);
            return new DeleteConfirmationCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
    }

}
