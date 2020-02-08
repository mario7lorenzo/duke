import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;
import duke.util.*;

import java.util.ArrayList;

/*
 * Duke
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Duke class is the main class of the bot,
 * where the command processing happens.</p>
 * @author Mario Lorenzo
 */

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private NoteStorage noteStorage;
    private NoteList noteList;
    private Parser parser;

    /**
     * Constructs the Duke instance that has a list that
     * stores tasks, as well as the list of all valid commands
     * that the Duke instance can perform, stored in a HashMap.
     */

    private Duke(TaskList taskList, Storage storage, NoteList noteList, Parser parser, NoteStorage noteStorage) {
        this.taskList = taskList;
        this.storage = storage;
        this.noteList = noteList;
        this.noteStorage = noteStorage;
        this.parser = parser;
    }

    /**
     * Creates a Duke instance with a factory method fashion.
     * A Duke instance with the list of tasks loaded will be created
     * if the file is formatted properly.
     * @return A Duke instance, loaded with the list of tasks.
     * @throws DukeInvalidTaskFormatException If the file is not formatted properly.
     * @throws DukeInvalidDateFormatException If the date of the deadline or event is not formatted properly.
     */

    public static Duke start() throws DukeInvalidTaskFormatException, DukeInvalidDateFormatException {
        Storage storage = new Storage("./data/tasks.txt");
        NoteStorage noteStorage = new NoteStorage("./data/notes.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Note> notes = new ArrayList<>();
        try {
            tasks = storage.load();
            notes = noteStorage.load();
        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            throw e;
        }
        TaskList taskList = new TaskList(tasks);
        NoteList noteList = new NoteList(notes);
        return new Duke(taskList, storage, noteList, new Parser(), noteStorage);
    }

    /**
     * Parses the input entered by the client.<br>
     *     The following are valid commands that Duke can process:
     *     <ul>
     *         <li><tt>list</tt> - lists all the tasks that Duke has stored.</li>
     *         <li><tt>done [index]</tt> - marks the task of a particular index as done.</li>
     *         <li><tt>delete [index]</tt> - deletes the task of a particular index.</li>
     *         <li><tt>todo [description]</tt> - adds the Todo task to the list.</li>
     *         <li><tt>deadline [description] /by [date]</tt> - adds the Deadline task to the list.</li>
     *         <li><tt>event [description] /at [date]</tt> - adds the Event task to the list.</li>
     *         <li><tt>find [keyword]</tt> - finds tasks using a keyword.</li>
     *     </ul>
     * @param commands The instruction provided by the client.
     */

    public String processCommand(String commands) {
        try {
            Command command = parser.parse(commands, taskList, noteList);
            return command.execute(taskList, storage, noteList, noteStorage);
        } catch (DukeException exc) {
            return exc.getMessage();
        }
    }
}