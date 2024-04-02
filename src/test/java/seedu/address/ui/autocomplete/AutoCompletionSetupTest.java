package seedu.address.ui.autocomplete;

import static org.mockito.Mockito.verify;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.scene.control.TextField;
import seedu.address.logic.commands.CommandData;
import seedu.address.ui.ResultDisplay;

@ExtendWith(MockitoExtension.class)
public class AutoCompletionSetupTest {

    @Mock
    private TextField commandTextField;

    @Mock
    private ResultDisplay resultDisplay;

    @Test
    public void setupAutoCompletion_bindsAutoCompletionToCommandTextFieldAndSetsCallback() {
        AutoCompletionSetup.setupAutoCompletion(commandTextField, resultDisplay);

        // Verify bindAutoCompletion call
        verify(TextFields.bindAutoCompletion(commandTextField, CommandData.getCommandWords()));

        // Retrieve the AutoCompletionBinding
        AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(commandTextField,
                CommandData.getCommandWords());

        // Verify setOnAutoCompleted callback
        verify(binding).setOnAutoCompleted(event -> {
            String selectedCommand = event.getCompletion();
            String followMessage = CommandData.getFollowMessage(selectedCommand);
            if (!followMessage.isEmpty()) {
                resultDisplay.setFeedbackToUser(followMessage);
            }
        });
    }
}