package seedu.address.ui;

import java.util.Objects;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.TransactionCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final String[] COMMANDS = {
        AddCommand.COMMAND_WORD,
        EditCommand.COMMAND_WORD,
        DeleteCommand.COMMAND_WORD,
        FindCommand.COMMAND_WORD,
        ListCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD,
        HelpCommand.COMMAND_WORD,
        TagCommand.COMMAND_WORD,
        TransactionCommand.COMMAND_WORD
    };
    private final CommandExecutor commandExecutor;
    private ResultDisplay resultDisplay;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, ResultDisplay resultDisplay) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.resultDisplay = resultDisplay; // Store the ResultDisplay reference

        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(commandTextField, COMMANDS);
        autoCompletionBinding.setOnAutoCompleted(event -> {
            String selectedCommand = event.getCompletion();
            if (Objects.equals(selectedCommand, AddCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser(AddCommand.FOLLOW_MESSAGE);
            } else if (Objects.equals(selectedCommand, EditCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser(EditCommand.FOLLOW_MESSAGE);
            } else if (Objects.equals(selectedCommand, DeleteCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser(DeleteCommand.FOLLOW_MESSAGE);
            } else if (Objects.equals(selectedCommand, FindCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser(FindCommand.FOLLOW_MESSAGE);
            } else if (Objects.equals(selectedCommand, TagCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser(TagCommand.FOLLOW_MESSAGE);
            }
        });
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
