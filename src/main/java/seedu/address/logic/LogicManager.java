package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteConfirmationCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ConfirmationStageParser;
import seedu.address.logic.parser.PayBackParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPayBack;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.storage.Storage;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final PayBackParser payBackParser;
    private final ConfirmationStageParser confirmationStageParser;
    private LogicState state = LogicState.NORMAL;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        payBackParser = new PayBackParser();
        confirmationStageParser = new ConfirmationStageParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = null;

        switch(state) {
        case NORMAL:
            command = payBackParser.parseCommand(model, commandText);
            break;
        case CONFIRMDELETE:
            command = confirmationStageParser.parseCommand(model, commandText);
            break;
        default:
        }

        assert command != null;

        commandResult = command.execute(model);

        if (command instanceof DeleteConfirmationCommand) {
            state = LogicState.CONFIRMDELETE;
        } else {
            state = LogicState.NORMAL;
        }

        try {
            storage.savePayBack(model.getPayBack());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPayBack getPayBack() {
        return model.getPayBack();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return model.getFilteredTransactionList();
    }

    @Override
    public ObservableList<Transaction> getEmptyTransactionList() {
        model.updateFilteredTransactionList(unused -> false);
        return model.getFilteredTransactionList();
    }

    @Override
    public Path getPayBackFilePath() {
        return model.getPayBackFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
