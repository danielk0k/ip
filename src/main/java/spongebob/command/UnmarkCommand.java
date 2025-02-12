package spongebob.command;

import spongebob.SpongebobException;
import spongebob.Storage;
import spongebob.TaskList;

/**
 * Represents a command to mark task as incomplete in the list.
 */
public class UnmarkCommand implements ICommand {
    private Integer[] indexList;

    /**
     * Returns an instance of UnmarkCommand.
     *
     * @param indexList List of indexes of task to be unmarked.
     */
    public UnmarkCommand(Integer[] indexList) {
        this.indexList = indexList;
    }

    /**
     * Executes the command by marking task as incomplete in the list.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        int numOfUnmarkedTask = 0;
        for (int index : this.indexList) {
            try {
                taskList.unmarkDone(index);
                numOfUnmarkedTask++;
            } catch (SpongebobException e) {
                System.out.println(e.getMessage());
            }
        }
        return numOfUnmarkedTask > 0
                ? String.format("%d task%s been marked incomplete.",
                        numOfUnmarkedTask, numOfUnmarkedTask > 1 ? "s have" : " has")
                : "Please select a task to be marked within the list.";
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
     * @param otherCmdList The index list of the other unmark command.
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
     * Returns if two UnmarkCommands are equal in index.
     *
     * @param obj Other command.
     * @return True if two UnmarkCommands are equal in index. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand otherCmd = (UnmarkCommand) obj;
            boolean isSameLength = this.indexList.length == otherCmd.indexList.length;
            return isSameLength && isSameElements(otherCmd.indexList);
        } else {
            return false;
        }
    }
}
