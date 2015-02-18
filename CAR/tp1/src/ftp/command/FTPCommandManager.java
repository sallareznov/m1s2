package ftp.command;

import java.util.LinkedList;
import java.util.List;

import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing a command manager
 */
public class FTPCommandManager {

	private List<FTPCommand> _commands;

	/**
	 * constructs a FTP command manager
	 */
	public FTPCommandManager() {
		_commands = new LinkedList<FTPCommand>();
	}

	/**
	 * adds a command to the manager
	 * @param command the command to add
	 */
	public void addCommand(FTPCommand command) {
		_commands.add(command);
	}

	/**
	 * executes a command
	 * @param commandString the command name
	 * @param argument the argument
	 * @param clientConfiguration the configuration of the client
	 */
	public void execute(String commandString, String argument,
			FTPClientConfiguration clientConfiguration) {
		for (final FTPCommand command : _commands) {
			if (command.accept(commandString)) {
				command.execute(argument, clientConfiguration);
				break;
			}
		}
	}

}
