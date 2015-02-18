package ftp;

import ftp.command.FTPCdupCommand;
import ftp.command.FTPCommandManager;
import ftp.command.FTPCwdCommand;
import ftp.command.FTPEprtCommand;
import ftp.command.FTPEpsvCommand;
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

	private static void usage() {
		System.err.println("java -jar ftpServer.jar [port] [baseDirectory]");
		System.err.println("\tport : port number (> 1023)");
		System.err.println("\tbaseDirectory : base directory");
	}

	public static void main(String[] args) {
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
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Le port est un entier > 1023"); 
		}
		if (port <= 1023) {
			throw new IllegalArgumentException("Le port est un entier > 1023"); 
		}
		final FTPServer ftpServer = new FTPServer(port,
				args[1], ftpDatabase);
		final FTPServerConfiguration serverConfiguration = ftpServer
				.getConfiguration();
		// création et initialisation du manager de commandes
		final FTPCommandManager commandManager = new FTPCommandManager();
		commandManager.addCommand(new FTPCwdCommand(ftpDatabase));
		commandManager.addCommand(new FTPCdupCommand(ftpDatabase));
		commandManager.addCommand(new FTPEprtCommand(ftpDatabase));
		commandManager.addCommand(new FTPEpsvCommand(ftpDatabase));
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
		System.out.println("--> FTP server opened on "
				+ serverConfiguration.getAddress() + ", port "
				+ serverConfiguration.getPort());
		System.out.println("--> Base directory is : " + serverConfiguration.getBaseDirectory());
		while (true) {
			ftpServer.connectToClient();
			System.out.println("--> New client connected on this server.");
			ftpServer.sendCommandWithDefaultMessage(
					serverConfiguration.getConnection(), 220);
			final FTPRequestHandler requestHandler = new FTPRequestHandler(
					ftpDatabase, serverConfiguration, commandManager);
			new Thread(requestHandler).start();
		}
	}

}
