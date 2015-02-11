package ftp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ftp.command.FTPCommand;
import ftp.command.FTPPassCommand;
import ftp.command.FTPUserCommand;

public class FTPCommandManager {
    
    private List<FTPCommand> _commands;
    
    public FTPCommandManager() {
	_commands = new LinkedList<FTPCommand>();
    }
    
    public void addDefaultCommands() {
	_commands.add(new FTPUserCommand());
	_commands.add(new FTPPassCommand());
    }
    
    public void execute(FTPClientConfiguration currentSession, String[] request) {
	final Iterator<FTPCommand> commandsIterator = _commands.iterator();
	while (commandsIterator.hasNext()) {
	    final FTPCommand currentCommand = commandsIterator.next();
	    if (currentCommand.accept(request)) {
		currentCommand.execute(currentSession, request);
		break;
	    }
	}
    }

}
