package ftp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {
	
	private static final int DEFAULT_PORT = 1500;

	public static void main(String[] args) throws IOException {
		
		final ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
		while (true) {
				final Socket connexion = serverSocket.accept();
				System.out
						.println("Un nouveau client s'est connecté à l'adresse "
								+ connexion.getLocalAddress()
								+ ", port "
								+ connexion.getLocalPort());
				final DataOutputStream stream = new DataOutputStream(connexion.getOutputStream());
				stream.writeBytes("220 FTP Server ready.");
				stream.writeBytes("\r\n");
				stream.flush();
				final FTPRequest newRequest = new FTPRequest(connexion);
				newRequest.start();
			
		}
	}

}
