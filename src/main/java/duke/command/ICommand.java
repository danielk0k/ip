package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * An interface for all command types.
 */
public interface ICommand {
    /**
     * Executes the command by adding the task to the list.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    String execute(Storage storage, TaskList taskList);

    /**
     * Returns if command is an ExitCommand.
     *
     * @return True if command is an ExitCommand. Else false.
     */
    boolean isExit();
}
