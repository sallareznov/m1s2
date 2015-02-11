package ftp.command;

import java.util.Iterator;
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

	public void execute(String[] requestTokens, FTPClientConfiguration clientConfiguration) {
		final Iterator<FTPCommand> commandsIterator = _commands.iterator();
		while (commandsIterator.hasNext()) {
			final FTPCommand currentCommand = commandsIterator.next();
			if (currentCommand.accept(requestTokens)) {
				currentCommand.execute(requestTokens, clientConfiguration);
				break;
			}
		}
	}

}
