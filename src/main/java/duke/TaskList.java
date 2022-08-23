package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] inputs = line.split(",");
            switch (inputs[0]) {
            case "T":
                add(new ToDo(inputs[2]));
                break;
            case "D":
                add(new Deadline(inputs[2], inputs[3]));
                break;
            case "E":
                add(new Event(inputs[2], inputs[3]));
                break;
            default:
                throw new DukeException("Invalid input from file.");
            }
        }
        sc.close();
        Ui.showMsg(new StringBuilder("Successfully loaded saved contents.\n" + this));
    }

    public String add(Task task) {
        this.list.add(task);
        StringBuilder stringBuilder = new StringBuilder("Got it. I've added this task:\n");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\nNow you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    public String remove(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be removed within the list.");
        }
        Task task = this.list.remove(index);
        StringBuilder stringBuilder = new StringBuilder("Noted. I've removed this task:\n");
        stringBuilder.append(task);
        stringBuilder.append(String.format("\nNow you have %d tasks in the list.", this.list.size()));
        return stringBuilder.toString();
    }

    public String markDone(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be marked within the list.");
        }
        this.list.get(index).markDone();
        return this.list.get(index).toString();
    }

    public String unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= this.list.size()) {
            throw new DukeException("Something went wrong!\nPlease select at task to be unmarked within the list.");
        }
        this.list.get(index).markNotDone();
        return this.list.get(index).toString();
    }

    public Iterator<Task> toSave() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            stringBuilder.append(String.format("%d.%s", i + 1, this.list.get(i).toString()));
            if (i < this.list.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
