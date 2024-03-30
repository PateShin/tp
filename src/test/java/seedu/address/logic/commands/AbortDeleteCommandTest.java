package seedu.address.logic.commands;

import static seedu.address.logic.commands.AbortDeleteCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPayBack;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AbortDeleteCommandTest {
    @Test
    public void execute_abortDelete_success() {
        AbortDeleteCommand command = new AbortDeleteCommand();

        Model modelStud = new ModelManager(getTypicalPayBack(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPayBack(), new UserPrefs());
        assertCommandSuccess(command, modelStud, MESSAGE_SUCCESS, expectedModel);
    }
}
