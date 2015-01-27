package ftp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FTPRequest extends Thread {

	private Socket _connexion;

	public FTPRequest(Socket connexion) {
		_connexion = connexion;
	}

	@Override
	public void run() {
		try {
			System.out.println("FTP request...");
			while (true) {
				final BufferedReader in = new BufferedReader(
						new InputStreamReader(_connexion.getInputStream()));
				System.out.println(in.readLine());
				final DataOutputStream stream = new DataOutputStream(_connexion.getOutputStream());
				stream.writeBytes("331 Guest login ok, send your complete e-mail address as password.");
				stream.writeBytes("\r\n");
				stream.flush();
			}
		} catch (IOException e) {
			// TODO
		}
	}

}
