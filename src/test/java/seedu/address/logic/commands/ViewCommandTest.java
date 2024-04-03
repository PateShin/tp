package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalPayBack;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;


public class ViewCommandTest {
    @Test
    public void execute_validPerson_success() {
        Model modelStub = new ModelManager(getTypicalPayBack(), new UserPrefs());
        Person personToView = modelStub.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Id id = personToView.getId();

        ViewCommand command = new ViewCommand(id);

        String expectedMessage = String.format(MESSAGE_VIEW_PERSON_SUCCESS, Messages.format(personToView));
        Model expectedModel = new ModelManager(getTypicalPayBack(), new UserPrefs());

        assertCommandSuccess(command, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        Model modelStub = new ModelManager(getTypicalPayBack(), new UserPrefs());
        Id id = new Id(999999);

        ViewCommand command = new ViewCommand(id);

        String expectedMessage = MESSAGE_INVALID_PERSON_DISPLAYED_ID;

        assertCommandFailure(command, modelStub, expectedMessage);
    }

    @Test
    public void equals() {
        ViewCommand firstCommand = new ViewCommand(new Id(240001));
        ViewCommand secondCommand = new ViewCommand(new Id(240002));

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
        ViewCommand viewCommand = new ViewCommand(targetId);
        String expected = ViewCommand.class.getCanonicalName() + "{targetIndex=" + targetId + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
