package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.IdEqualsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagEqualsPredicate;
import seedu.address.model.person.YearJoinedEqualsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "/find :name Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "/find :name Alice      Bob  ", expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("67890")));
        assertParseSuccess(parser, "/find :phone 67890", expectedFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("example@example.com")));
        assertParseSuccess(parser, "/find :email example@example.com", expectedFindCommand);
    }

    @Test
    public void parse_validIdArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new IdEqualsPredicate("240001"));
        assertParseSuccess(parser, "/find :id 240001", expectedFindCommand);
    }

    @Test
    public void parse_validYearJoinedArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new YearJoinedEqualsPredicate("2019"));
        assertParseSuccess(parser, "/find :year 2019", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagEqualsPredicate("intern"));
        assertParseSuccess(parser, "/find :tag intern", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "/find :name Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "/find :name \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // invalid prefix
        assertParseFailure(parser, "/find :unknown Patrick",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyName_throwsParseException() {
        String expectedMessage = "Name to find cannot be empty.";
        assertParseFailure(parser, "/find :name", expectedMessage);
    }

    @Test
    public void parse_emptyPhone_throwsParseException() {
        String expectedMessage = "Phone number to find cannot be empty.";
        assertParseFailure(parser, "/find :phone", expectedMessage);
    }

    @Test
    public void parse_emptyEmail_throwsParseException() {
        String expectedMessage = "Email to find cannot be empty.";
        assertParseFailure(parser, "/find :email", expectedMessage);
    }

    @Test
    public void parse_emptyId_throwsParseException() {
        String expectedMessage = "ID to find cannot be empty.";
        assertParseFailure(parser, "/find :id", expectedMessage);
    }

    @Test
    public void parse_emptyYearJoined_throwsParseException() {
        String expectedMessage = "Year joined to find cannot be empty.";
        assertParseFailure(parser, "/find :year", expectedMessage);
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        String expectedMessage = "Tag to find cannot be empty.";
        // Assuming tag prefix is "t/"
        assertParseFailure(parser, "/find :tag", expectedMessage);
    }

}
