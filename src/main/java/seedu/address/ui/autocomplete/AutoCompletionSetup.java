package seedu.address.ui.autocomplete;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.scene.control.TextField;
import seedu.address.logic.commands.CommandData;
import seedu.address.ui.ResultDisplay;

/**
 * Sets up auto-completion for the command text field.
 */
public class AutoCompletionSetup {

    /**
     * Configures the auto-completion feature on the given text field to suggest command words.
     * @param commandTextField The text field where commands are entered.
     * @param resultDisplay The UI component to display follow-up messages.
     */
    public static void setupAutoCompletion(TextField commandTextField, ResultDisplay resultDisplay) {
        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(commandTextField,
                CommandData.getCommandWords());
        autoCompletionBinding.setOnAutoCompleted(event -> {
            String selectedCommand = event.getCompletion();
            String followMessage = CommandData.getFollowMessage(selectedCommand);
            if (!followMessage.isEmpty()) {
                resultDisplay.setFeedbackToUser(followMessage);
            }
        });
    }
}

