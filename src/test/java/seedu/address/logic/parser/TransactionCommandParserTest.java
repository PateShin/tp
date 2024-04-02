package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYEEID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPLOYEEID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYEEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalTransactions.TRANSACTION_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.TransactionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Id;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TransactionBuilder;

public class TransactionCommandParserTest {

    private TransactionCommandParser parser;

    @BeforeEach
    public void setUp() {
        // Set up the model manager
        Model modelManager = new ModelManager();
        modelManager.addPerson(new PersonBuilder(AMY).withId(VALID_EMPLOYEEID).build());

        // Set up the parser
        parser = new TransactionCommandParser(modelManager);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TransactionCommand.MESSAGE_USAGE);

        // missing employeeId prefix
        assertParseFailure(parser, AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC, expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, EMPLOYEEID_DESC + DESCRIPTION_DESC + DATETIME_DESC, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, EMPLOYEEID_DESC + AMOUNT_DESC + DATETIME_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid employeeId
        assertParseFailure(parser, INVALID_EMPLOYEEID_DESC + AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC,
                Id.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, EMPLOYEEID_DESC + INVALID_AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, EMPLOYEEID_DESC + AMOUNT_DESC + DESCRIPTION_DESC + INVALID_DATETIME_DESC,
                DateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicateEmployeeIdPrefix_throwsException() {
        String input = EMPLOYEEID_DESC + " " + EMPLOYEEID_DESC + AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC;
        assertParseFailure(parser, input, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));
    }

    @Test
    public void parse_duplicateAmountPrefix_throwsException() {
        String input = EMPLOYEEID_DESC + AMOUNT_DESC + " " + AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC;
        assertParseFailure(parser, input, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));
    }

    @Test
    public void parse_duplicateDescriptionPrefix_throwsException() {
        String input = EMPLOYEEID_DESC + AMOUNT_DESC + DESCRIPTION_DESC + " " + DESCRIPTION_DESC + DATETIME_DESC;
        assertParseFailure(parser, input, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }

    @Test
    public void parse_duplicateDateTimePrefix_throwsException() {
        String input = EMPLOYEEID_DESC + AMOUNT_DESC + DESCRIPTION_DESC + DATETIME_DESC + " " + DATETIME_DESC;
        assertParseFailure(parser, input, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));
    }
}
