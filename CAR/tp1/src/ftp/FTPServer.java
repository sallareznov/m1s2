package ftp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class FTPServer {

	public static void main(String[] args) throws IOException {
		try {
			final ServerSocket serverSocket = new ServerSocket(1500);
			final Socket connexion = serverSocket.accept();
			System.out.println("Un client vient de se connecter à l'adresse "
					+ connexion.getLocalAddress() + ", port " + connexion.getLocalPort());
			connexion.getOutputStream().write(65);
			while (true) {
				final InputStream stream = connexion.getInputStream();
				int code;
				while ((code = stream.read()) != -1) {
					System.out.println(Character.toChars(code)[0]);
				}
				connexion.close();
				serverSocket.close();
			}
		} catch (SocketException e) {
			System.out.println("Le client a fermé la connexion.");
		}
	}

}
