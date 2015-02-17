package ftp.command;

import ftp.FTPDatabase;
import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

/**
 * Class representing the CWD command 
 */
public class FTPCwdCommand extends FTPMessageSender implements FTPCommand {

	/**
	 * constructs a CWD-command
	 * @param database the database
	 */
	public FTPCwdCommand(FTPDatabase database) {
		super(database);
	}

	@Override
	public boolean accept(String command) {
		return command.equals("CWD");
	}

	@Override
	public void execute(String argument,
			FTPClientConfiguration clientConfiguration) {
		final String newDirectory = clientConfiguration.getWorkingDirectory() + clientConfiguration.getDirectorySeparator() + argument;
		//if (new File(clientConfiguration.getWorkingDirectory()).)
		clientConfiguration.setWorkingDirectory(argument);
//		if (argument.equals("..")) {
//			int ind = filename.lastIndexOf(fileSeparator);
//			if (ind > 0) {
//				filename = filename.substring(0, ind);
//			}
//		}
//
//		//
//		// Don't do anything if the user did "cd .". In the other cases,
//		// append the argument to the current directory.
//		//
//		else if ((args != null) && (!args.equals("."))) {
//			filename = filename + fileSeparator + args;
//		}
//
//		//
//		// Now make sure that the specified directory exists, and doesn't
//		// attempt to go to the FTP root's parent directory. Note how we
//		// use a "File" object to test if a file exists, is a directory, etc.
//		//
//		File f = new File(filename);
//
//		if (f.exists() && f.isDirectory()
//				&& (filename.length() >= root.length())) {
//			currDirectory = filename;
//			out.println("250 The current directory has been changed to "
//					+ currDirectory);
//		} else {
//			out.println("550 Requested action not taken. File unavailable.");
//		}

	}

}
