package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AbortDeleteCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConfirmationStageParserTest {
    private ConfirmationStageParser parser = new ConfirmationStageParser();
    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        assertTrue(parser.parseCommand("y") instanceof DeleteCommand);
        assertTrue(parser.parseCommand("Y") instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_abortDeleteCommand_success() throws Exception {
        assertTrue(parser.parseCommand("n") instanceof AbortDeleteCommand);
        assertTrue(parser.parseCommand("N") instanceof AbortDeleteCommand);
    }

    @Test
    public void parseCommand_invalidUserInput_throwsParserException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand(""));
    }
}
