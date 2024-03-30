package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteConfirmationCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalPayBack;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

public class DeleteConfirmationCommandTest {
    @Test
    public void execute_validPerson_success() {
        Model modelStub = new ModelManager(getTypicalPayBack(), new UserPrefs());
        Person personToDelete = modelStub.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id id = personToDelete.getId();

        DeleteConfirmationCommand command = new DeleteConfirmationCommand(id);

        String expectedMessage = String.format(MESSAGE_SUCCESS, Messages.format(personToDelete));
        Model expectedModel = new ModelManager(getTypicalPayBack(), new UserPrefs());

        assertCommandSuccess(command, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        Model modelStub = new ModelManager(getTypicalPayBack(), new UserPrefs());

        DeleteConfirmationCommand command = new DeleteConfirmationCommand(new Id(999999));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID;

        assertCommandFailure(command, modelStub, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteConfirmationCommand firstCommand = new DeleteConfirmationCommand(new Id(240001));
        DeleteConfirmationCommand secondCommand = new DeleteConfirmationCommand(new Id(240002));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different values -> return false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void toStringMethod() {
        Id targetId = new Id(240001);
        DeleteConfirmationCommand deleteConfirmationCommand = new DeleteConfirmationCommand(targetId);
        String expected = DeleteConfirmationCommand.class.getCanonicalName() + "{targetIndex=" + targetId + "}";
        assertEquals(expected, deleteConfirmationCommand.toString());
    }

}
