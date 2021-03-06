package ftp.server.command;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPRequest;

/**
 * Class representing a command manager. When a command arrives to the request handler,
 * the manager checks if a command in its list accepts that request, and executes it
 */
public class FTPCommandManager {

	private List<FTPCommand> commands;

	/**
	 * constructs a FTP command manager
	 */
	public FTPCommandManager() {
		commands = new LinkedList<FTPCommand>();
	}

	/**
	 * adds a command to the manager
	 * 
	 * @param command
	 *            the command to add
	 */
	public void addCommand(FTPCommand command) {
		commands.add(command);
	}

	/**
	 * executes a command
	 * 
	 * @param request
	 *            the request (command + argument)
	 * @param clientConfiguration
	 *            the configuration of the client
	 * @throws IOException if an I/O error occurs
	 */
	public void handle(FTPRequest request,
			FTPClientConfiguration clientConfiguration) throws IOException {
		for (final FTPCommand command : commands) {
			if (command.accept(request)) {
				command.execute(request, clientConfiguration);
				break;
			}
		}
	}

}
