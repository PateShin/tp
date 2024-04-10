package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_CONFIRMATION_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AbortDeleteCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ConfirmationStageParserTest {
    private ConfirmationStageParser parser = new ConfirmationStageParser();
    private Model model = new ModelManager();
    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        assertTrue(parser.parseCommand(model, "y") instanceof DeleteCommand);
        assertTrue(parser.parseCommand(model, "Y") instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_abortDeleteCommand_success() throws Exception {
        assertTrue(parser.parseCommand(model, "n") instanceof AbortDeleteCommand);
        assertTrue(parser.parseCommand(model, "N") instanceof AbortDeleteCommand);
    }

    @Test
    public void parseCommand_invalidUserInput_throwsParserException() {
        Person person = new PersonBuilder().build();
        model.setLastMentionedPerson(person);
        String expectedMessage = String.format(MESSAGE_UNKNOWN_CONFIRMATION_COMMAND,
                Messages.format(person)
        );
        assertThrows(ParseException.class, expectedMessage, ()
                -> parser.parseCommand(model, ""));
    }
}
