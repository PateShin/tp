package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.Id;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();
    @Test
    public void parse_validInput_success() {
        String userInput = "240001";

        ViewCommand expectedCommand = new ViewCommand(new Id(240001));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        String userInput = "";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        String userInput = "test";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
