package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.transaction.Transaction;

import java.util.function.Predicate;

public class ViewCommandParser implements Parser<ViewCommand> {
    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        try {
            Id id = ParserUtil.parseId(userInput);
            Predicate<Transaction> predicate = transaction -> transaction.getEmployeeId().equals(id);

            return new ViewCommand(id, predicate);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}
