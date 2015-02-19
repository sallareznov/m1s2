package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the SYST command
 */
public class FTPSystCommand extends FTPMessageSender implements FTPCommand {
	
	/**
	 * constructs a SYST command
	 * @param database the database
	 */
	public FTPSystCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("SYST");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		sendCommand(clientConfiguration.getConnection(), 215);
	}

}
