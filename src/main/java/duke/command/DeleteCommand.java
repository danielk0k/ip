package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to delete task from the list.
 */
public class DeleteCommand implements ICommand {
    private int index;

    /**
     * Returns an instance of DeleteCommand.
     * @param index Index of task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting specified task from the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        try {
            return taskList.delete(index);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns if command is an ExitCommand.
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if two DeleteCommands are equal in index of task to be deleted.
     * @param obj Other command.
     * @return True if two DeleteCommands are equal in index of task to be deleted. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand otherCmd = (DeleteCommand) obj;
            return this.index == otherCmd.index;
        } else {
            return false;
        }
    }
}
