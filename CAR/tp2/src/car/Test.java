package car;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException, IOException {
		final FTPClient client = new FTPClient();
		client.connect("127.0.0.1", 1500);
		System.out.println(client.getReplyString());
		client.login("test", "test");
		System.out.println(client.printWorkingDirectory());
		client.changeWorkingDirectory("test");
		System.out.println(client.printWorkingDirectory());
		client.cdup();
		System.out.println(client.printWorkingDirectory());
		System.out.println(client.listNames().length);
		for (FTPFile file : client.listFiles()) {
			System.out.println(file.getName());
		}
		for (FTPFile file : client.listDirectories()) {
			System.out.println(file);
		}
	}

}
