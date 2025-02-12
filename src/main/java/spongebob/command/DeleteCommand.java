package spongebob.command;

import spongebob.Storage;
import spongebob.TaskList;

/**
 * Represents a command to delete task from the list.
 */
public class DeleteCommand implements ICommand {
    private Integer[] indexList;

    /**
     * Returns an instance of DeleteCommand.
     *
     * @param indexList List of indexes of task to be deleted.
     */
    public DeleteCommand(Integer[] indexList) {
        this.indexList = indexList;
    }

    /**
     * Executes the command by deleting tasks from the list.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.delete(this.indexList);
    }

    /**
     * Returns if command is an ExitCommand.
     *
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if the arrays of indexes are the same.
     * @param otherCmdList The index list of the other delete command.
     * @return True if the lists are the same in element and order. Else false.
     */
    private boolean isSameElements(Integer[] otherCmdList) {
        for (int i = 0; i < this.indexList.length; i++) {
            if (this.indexList[i] != otherCmdList[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns if two DeleteCommands are equal in index of task to be deleted.
     *
     * @param obj Other command.
     * @return True if two DeleteCommands are equal in index of task to be deleted. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand otherCmd = (DeleteCommand) obj;
            boolean isSameLength = this.indexList.length == otherCmd.indexList.length;
            return isSameLength && isSameElements(otherCmd.indexList);
        } else {
            return false;
        }
    }
}
