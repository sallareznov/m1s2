package ftp.command;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import ftp.FTPMessageSender;
import ftp.configuration.FTPClientConfiguration;

public class FTPPortCommand extends FTPMessageSender implements FTPCommand {

    @Override
    public boolean accept(String command) {
	return command.equals("PORT");
    }

    @Override
    public void execute(String argument, FTPClientConfiguration clientConfiguration) {
	final String[] portArguments = argument.split(",");
	final String ipAddress = portArguments[0] + "." + portArguments[1] + "." + portArguments[2] + "." + portArguments[3];
	final int port = (Integer.parseInt(portArguments[4]) * 256) + Integer.parseInt(portArguments[5], 16);
	System.out.println("address = " + ipAddress);
	System.out.println("port = " + port);
	sendCommandWithDefaultMessage(clientConfiguration.getConnection(), 200);
	try {
	    final Socket dataSocket = new Socket(InetAddress.getByName(ipAddress), port);
	    clientConfiguration.setDataSocket(dataSocket);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Error while opening the socket.");
	}
    }

}
