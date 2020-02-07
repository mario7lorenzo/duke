package duke.command;

import duke.util.ArchiveList;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * ByeCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 06 Feb 2020
 *
 */

/**
 * ByeCommand class extends a Command abstract class
 * and it represents the terminating command when a user
 * wants to end using Duke.
 * @author Mario Lorenzo
 */

public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand instance.
     */

    public ByeCommand() {

    }

    /**
     * Executes the command by returning the exit message.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The exit message.
     */

    @Override
    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    @Override
    public boolean isByeCommand() {
        return true;
    }
}
