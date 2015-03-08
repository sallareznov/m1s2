package ftp;

import java.io.IOException;

import org.apache.log4j.Logger;

import ftp.client.FTPRequestHandler;
import ftp.server.FTPServer;
import ftp.server.command.FTPCdupCommand;
import ftp.server.command.FTPCommandManager;
import ftp.server.command.FTPCwdCommand;
import ftp.server.command.FTPEprtCommand;
import ftp.server.command.FTPEpsvCommand;
import ftp.server.command.FTPListCommand;
import ftp.server.command.FTPNlstCommand;
import ftp.server.command.FTPNotImplementedCommand;
import ftp.server.command.FTPPassCommand;
import ftp.server.command.FTPPasvCommand;
import ftp.server.command.FTPPortCommand;
import ftp.server.command.FTPPwdCommand;
import ftp.server.command.FTPQuitCommand;
import ftp.server.command.FTPRetrCommand;
import ftp.server.command.FTPStorCommand;
import ftp.server.command.FTPSystCommand;
import ftp.server.command.FTPTypeCommand;
import ftp.server.command.FTPUserCommand;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPLoggerFactory;
import ftp.shared.FTPServerConfiguration;

/**
 * Main class
 */
public class Main {
	
	private static final Logger LOGGER = FTPLoggerFactory.create(Main.class);
	
	private Main() {
		// Utility class = private constructor to hide the implicit public one.
	}

	/**
	 * Prints the usage of the program
	 */
	public static void usage() {
		LOGGER.info("java -jar ftpServer.jar [port] [baseDirectory]");
		LOGGER.info("\tport : port number (> 1023)");
		LOGGER.info("\tbaseDirectory : base directory");
	}

	/**
	 * Main method
	 * @param args
	 * @throws IOException if an I/O error occurs
	 */
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			usage();
			return;
		}
		// l'unique base de données qui sera partagée est initialisée
		final FTPDatabase ftpDatabase = new FTPDatabase();
		// création d'un serveur FTP
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Le port est un entier > 1023");
		}
		if (port <= 1023) {
			throw new IllegalArgumentException("Le port est un entier > 1023");
		}
		final FTPServer ftpServer = new FTPServer(port, args[1], ftpDatabase);
		final FTPServerConfiguration serverConfiguration = ftpServer
				.getConfiguration();
		// création et initialisation du manager de commandes
		final FTPCommandManager commandManager = new FTPCommandManager();
		commandManager.addCommand(new FTPCwdCommand(ftpDatabase));
		commandManager.addCommand(new FTPCdupCommand(ftpDatabase));
		commandManager.addCommand(new FTPEprtCommand(ftpDatabase));
		commandManager.addCommand(new FTPEpsvCommand(ftpDatabase));
		commandManager.addCommand(new FTPListCommand(ftpDatabase));
		commandManager.addCommand(new FTPNlstCommand(ftpDatabase));
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
		LOGGER.info("--> FTP server opened on "
				+ serverConfiguration.getAddress() + ", port "
				+ serverConfiguration.getPort());
		LOGGER.info("--> Base directory is : "
				+ serverConfiguration.getBaseDirectory());
		while (true) {
			ftpServer.connectToClient();
			LOGGER.info("--> New client connected on this server.");
			ftpServer.sendCommand(serverConfiguration.getConnection(), 220);
			final FTPRequestHandler requestHandler = new FTPRequestHandler(
					ftpDatabase, serverConfiguration, commandManager);
			new Thread(requestHandler).start();
		}
	}

}
