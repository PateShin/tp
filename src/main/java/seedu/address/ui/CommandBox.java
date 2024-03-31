package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import java.util.Objects;



/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private ResultDisplay resultDisplay;

    private static final String[] COMMANDS = {
            AddCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            TagCommand.COMMAND_WORD
    };

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
                this.resultDisplay.setFeedbackToUser("Follows:\n" + ":name NAME :phone PHONE :email EMAIL " +
                        ":address ADDRESS :year YEAR_JOINED :tag TAG");
            } else if (Objects.equals(selectedCommand, EditCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser("Follows:\n" + "ID :phone PHONE and/or :email EMAIL " +
                        "and/or :tag TAG");
            } else if (Objects.equals(selectedCommand, DeleteCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser("Follows:\n" + "ID");
            } else if (Objects.equals(selectedCommand, FindCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser("Follows:\n" + ":id, :name, :phone, :email, :year, :tag");
            } else if (Objects.equals(selectedCommand, TagCommand.COMMAND_WORD)) {
                this.resultDisplay.setFeedbackToUser("Follows:\n" + ":ID :tag TAG");
            }
        });
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
