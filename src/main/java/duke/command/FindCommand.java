package duke.command;

import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * FindCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>FindCommand class extends the Command abstract class
 * and it describes the behavior of the commands regarding to
 * the searching of tasks using keyword.</p>
 * @author Mario Lorenzo
 */

public class FindCommand extends Command {
    private String key;

    /**
     * Constructs a FindCommand instance.
     * @param key The keyword fot searching a task.
     */

    public FindCommand(String key) {
        this.key = key;
    }

    /**
     * Executes the find command.
     * @param taskList The list of the tasks.
     * @param storage The storage of the Duke.
     * @return The outcome message.
     */

    @Override
    public String execute(TaskList taskList, Storage storage, NoteList noteList, NoteStorage noteStorage) {
        return taskList.findTask(key);
    }

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    @Override
    public boolean isByeCommand() {
        return false;
    }
}

