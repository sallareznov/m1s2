package ftp.command;

import java.util.LinkedList;
import java.util.List;

import ftp.configuration.FTPClientConfiguration;

public class FTPCommandManager {

	private List<FTPCommand> _commands;

	public FTPCommandManager() {
		_commands = new LinkedList<FTPCommand>();
	}

	public void addCommand(FTPCommand command) {
		_commands.add(command);
	}

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
