package seedu.address.logic.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandData {
    private static final Map<String, String> commandFollowMessages = new HashMap<>();

    static {
        commandFollowMessages.put(AddCommand.COMMAND_WORD, AddCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(EditCommand.COMMAND_WORD, EditCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(DeleteCommand.COMMAND_WORD, DeleteCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(FindCommand.COMMAND_WORD, FindCommand.FOLLOW_MESSAGE);
        commandFollowMessages.put(TagCommand.COMMAND_WORD, TagCommand.FOLLOW_MESSAGE);
        // Add other commands and their follow messages here
    }

    public static String getFollowMessage(String commandWord) {
        return commandFollowMessages.getOrDefault(commandWord, "");
    }

    public static String[] getCommandWords() {
        return commandFollowMessages.keySet().toArray(new String[0]);
    }
}

