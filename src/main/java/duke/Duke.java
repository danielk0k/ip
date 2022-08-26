package duke;

import duke.command.EmptyCommand;
import duke.command.ICommand;

/**
 * Represents the main class of the Duke program.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Returns an instance of Duke.
     * @param filePath String location of duke.txt.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.load());
        this.ui = new Ui();
        ui.showWelcomeMsg();
    }

    /**
     * Starts Duke program.
     */
    public void run() {
        ICommand cmd = new EmptyCommand();
        while (!cmd.isExit()) {
            String input = this.ui.readCommand();
            cmd = Parser.parse(input);
            cmd.execute(storage, taskList, ui);
        }
    }

    /**
     * Main method for Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke dk = new Duke("data/duke.txt");
        dk.run();
    }
}
