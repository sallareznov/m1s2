package ftp;

import ftp.command.FTPCdupCommand;
import ftp.command.FTPCommandManager;
import ftp.command.FTPCwdCommand;
import ftp.command.FTPListCommand;
import ftp.command.FTPNotImplementedCommand;
import ftp.command.FTPPassCommand;
import ftp.command.FTPPasvCommand;
import ftp.command.FTPPortCommand;
import ftp.command.FTPPwdCommand;
import ftp.command.FTPQuitCommand;
import ftp.command.FTPRetrCommand;
import ftp.command.FTPStorCommand;
import ftp.command.FTPSystCommand;
import ftp.command.FTPTypeCommand;
import ftp.command.FTPUserCommand;
import ftp.configuration.FTPServerConfiguration;

public class Main {

	public static void main(String[] args) {
		final FTPServer ftpServer = new FTPServer(1500,
				System.getProperty("user.home"));
		final FTPServerConfiguration serverConfiguration = ftpServer
				.getConfiguration();
		final FTPCommandManager commandManager = new FTPCommandManager();
		commandManager.addCommand(new FTPCwdCommand());
		commandManager.addCommand(new FTPCdupCommand());
		commandManager.addCommand(new FTPListCommand());
		commandManager.addCommand(new FTPPassCommand());
		commandManager.addCommand(new FTPPasvCommand());
		commandManager.addCommand(new FTPPortCommand());
		commandManager.addCommand(new FTPPwdCommand());
		commandManager.addCommand(new FTPQuitCommand());
		commandManager.addCommand(new FTPRetrCommand());
		commandManager.addCommand(new FTPStorCommand());
		commandManager.addCommand(new FTPSystCommand());
		commandManager.addCommand(new FTPTypeCommand());
		commandManager.addCommand(new FTPUserCommand());
		commandManager.addCommand(new FTPNotImplementedCommand());
		System.out.println("--> FTP server opened on "
				+ serverConfiguration.getAddress() + ", port "
				+ serverConfiguration.getPort());
		while (true) {
			ftpServer.connectToClient();
			serverConfiguration.addClient();
			System.out.println("--> New client connected on this server.");
			System.out.println("--> total clients : "
					+ serverConfiguration.getNbClients() + ".");
			ftpServer.sendCommandWithDefaultMessage(
					serverConfiguration.getConnection(), 220);
			final FTPRequestHandler requestHandler = new FTPRequestHandler(
					serverConfiguration, commandManager);
			new Thread(requestHandler).start();
		}
	}

}
