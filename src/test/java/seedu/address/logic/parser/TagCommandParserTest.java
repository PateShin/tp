package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.model.person.Id;
import seedu.address.model.tag.Tag;

public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_singleTag_success() {
        String userInput = "240001 " + PREFIX_TAG + " Finance";

        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Finance"));

        TagCommand expectedCommand = new TagCommand(new Id(240001), tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleTag_success() {
        String userInput = "240001 "
                + PREFIX_TAG + " Finance "
                + PREFIX_TAG + " Management "
                + PREFIX_TAG + " Family";

        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Finance"));
        tags.add(new Tag("Family"));
        tags.add(new Tag("Management"));

        TagCommand expectedCommand = new TagCommand(new Id(240001), tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyId_throwsParseException() {
        String userInput = PREFIX_TAG + " Finance";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidId_throwsParseException() {
        String userInput = "0 " + PREFIX_TAG + " Finance";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

}
