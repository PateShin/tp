package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandDataTest {

    @Test
    void testCommandFollowMessagesInitialization() {
        assertEquals(AddCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(AddCommand.COMMAND_WORD));
        assertEquals(EditCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(EditCommand.COMMAND_WORD));
        assertEquals(DeleteCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(DeleteCommand.COMMAND_WORD));
        assertEquals(FindCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(FindCommand.COMMAND_WORD));
        assertEquals(TagCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(TagCommand.COMMAND_WORD));
        assertEquals(ExitCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(ExitCommand.COMMAND_WORD));
        assertEquals(HelpCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(HelpCommand.COMMAND_WORD));
        assertEquals(TransactionCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(TransactionCommand.COMMAND_WORD));
        assertEquals(ListCommand.FOLLOW_MESSAGE, CommandData.getFollowMessage(ListCommand.COMMAND_WORD));
    }

    @Test
    void testGetFollowMessageWithNonexistentCommand() {
        // Test to ensure a non-existent command returns an empty string
        String nonexistentCommand = "nonexistent";
        assertTrue(CommandData.getFollowMessage(nonexistentCommand).isEmpty());
    }

    @Test
    void testGetCommandWords() {
        // Test to ensure getCommandWords returns all command words correctly
        String[] commandWords = CommandData.getCommandWords();
        String[] expectedCommandWords = {
            AddCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            TagCommand.COMMAND_WORD,
            TransactionCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD
        };

        for (String expectedCommandWord : expectedCommandWords) {
            assertTrue(java.util.Arrays.asList(commandWords).contains(expectedCommandWord));
        }
    }
}
