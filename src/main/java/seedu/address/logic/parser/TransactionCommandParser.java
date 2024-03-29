package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.TransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Transaction;

/**
 * Parses input arguments and creates a new TransactionCommand object
 */
public class TransactionCommandParser implements Parser<TransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TransactionCommand
     * and returns a TransactionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TransactionCommand parse(String args) throws ParseException {
        if (args.matches("\\s+[^;].*(?:;\\s+[^;].*){2,3}")) {
            args = Parser.appendPrefixes(args, PREFIX_ID, PREFIX_AMOUNT, PREFIX_DESCRIPTION, PREFIX_DATETIME);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_AMOUNT, PREFIX_DESCRIPTION, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_AMOUNT, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TransactionCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_AMOUNT, PREFIX_DESCRIPTION, PREFIX_DATETIME);
        Id employeeId = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        DateTime dateTime;
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        } else {
            dateTime = new DateTime(LocalDateTime.now());
        }

        Transaction transaction = new Transaction(employeeId, amount, description, dateTime);
        return new TransactionCommand(transaction);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
