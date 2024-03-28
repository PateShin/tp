package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteConfirmationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteConfirmationCommandParser implements Parser<DeleteConfirmationCommand>{

    @Override
    public DeleteConfirmationCommand parse(String args) throws ParseException {
        try {
            Id id = ParserUtil.parseId(args);
            return new DeleteConfirmationCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
