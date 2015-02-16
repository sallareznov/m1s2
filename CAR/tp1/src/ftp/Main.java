package ftp;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

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
		final FTPDatabase ftpDatabase = new FTPDatabase();
		final FTPServer ftpServer = new FTPServer(1500,
				System.getProperty("user.home"), ftpDatabase);
		final FTPServerConfiguration serverConfiguration = ftpServer
				.getConfiguration();
		final FTPCommandManager commandManager = new FTPCommandManager();
		commandManager.addCommand(new FTPCwdCommand(ftpDatabase));
		commandManager.addCommand(new FTPCdupCommand(ftpDatabase));
		commandManager.addCommand(new FTPListCommand(ftpDatabase));
		commandManager.addCommand(new FTPPassCommand(ftpDatabase));
		commandManager.addCommand(new FTPPasvCommand(ftpDatabase));
		commandManager.addCommand(new FTPPortCommand(ftpDatabase));
		commandManager.addCommand(new FTPPwdCommand(ftpDatabase));
		commandManager.addCommand(new FTPQuitCommand(ftpDatabase));
		commandManager.addCommand(new FTPRetrCommand(ftpDatabase));
		commandManager.addCommand(new FTPStorCommand(ftpDatabase));
		commandManager.addCommand(new FTPSystCommand(ftpDatabase));
		commandManager.addCommand(new FTPTypeCommand(ftpDatabase));
		commandManager.addCommand(new FTPUserCommand(ftpDatabase));
		commandManager.addCommand(new FTPNotImplementedCommand(ftpDatabase));
		final Logger logger = Logger.getLogger(Main.class);
		final ConsoleAppender console = new ConsoleAppender();
		console.setLayout(new SimpleLayout());
		console.activateOptions();
		logger.addAppender(console);
		try {
			logger.addAppender(new FileAppender(new HTMLLayout(), "test.html"));
		} catch (IOException e) {
		}
		logger.info("--> FTP server opened on "
				+ serverConfiguration.getAddress() + ", port "
				+ serverConfiguration.getPort());
		// System.out.println("--> FTP server opened on "
		// + serverConfiguration.getAddress() + ", port "
		// + serverConfiguration.getPort());
		while (true) {
			ftpServer.connectToClient();
			serverConfiguration.addClient();
			System.out.println("--> New client connected on this server.");
			System.out.println("--> total clients : "
					+ serverConfiguration.getNbClients() + ".");
			ftpServer.sendCommandWithDefaultMessage(
					serverConfiguration.getConnection(), 220);
			final FTPRequestHandler requestHandler = new FTPRequestHandler(
					ftpDatabase, serverConfiguration, commandManager);
			new Thread(requestHandler).start();
		}
	}

}
