package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;

    /**
     * Appends prefixes to the given arguments.
     * @param args Arguments to append prefixes to.
     * @param prefixes Prefixes to append.
     * @return Arguments with prefixes appended.
     */
    static String appendPrefixes(String args, Prefix... prefixes) {
        StringBuilder result = new StringBuilder(args);
        String[] argsArr = args.trim().split(";\\s+");
        for (int i = 0; i < Math.min(prefixes.length, argsArr.length); i++) {
            result.append(" ").append(prefixes[i]).append(" ").append(argsArr[i]);
        }
        return result.toString();
    }
}
