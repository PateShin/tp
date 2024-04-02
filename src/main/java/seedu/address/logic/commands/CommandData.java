package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates command words and their associated follow-up messages for auto-completion and guidance.
 * This class provides a central point of reference for command words and messages,
 */
public class CommandData {

    /**
     * A map holding command words as keys and their associated follow-up messages as values.
     */
    private static final Map<String, String> commandFollowMessages = new HashMap<>();

    static {
        commandFollowMessages.put(AddCommand.COMMAND_WORD, AddCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(EditCommand.COMMAND_WORD, EditCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(ExitCommand.COMMAND_WORD, null);
        commandFollowMessages.put(DeleteCommand.COMMAND_WORD, DeleteCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(FindCommand.COMMAND_WORD, FindCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(HelpCommand.COMMAND_WORD, null);
        commandFollowMessages.put(TagCommand.COMMAND_WORD, TagCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(TransactionCommand.COMMAND_WORD, null);
        commandFollowMessages.put(ListCommand.COMMAND_WORD, null);
    }

    /**
     * Retrieves the follow-up message associated with a given command word.
     *
     * @param commandWord The command word whose follow-up message is to be retrieved.
     * @return The follow-up message for the given command word; an empty string if none exists.
     */
    public static String getFollowMessage(String commandWord) {
        return commandFollowMessages.getOrDefault(commandWord, "");
    }

    /**
     * Returns an array of all command words.
     *
     * A complete list of command words defined in the application.
     *
     * @return An array containing all command words.
     */
    public static String[] getCommandWords() {
        return commandFollowMessages.keySet().toArray(new String[0]);
    }
}

