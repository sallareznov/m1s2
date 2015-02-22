package ftp.command;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ftp.FTPDatabase;
import ftp.configuration.FTPClientConfiguration;

/**
 * @author  diagne
 */
public class FTPQuitCommandTest {

	/**
	 * @uml.property  name="_quitCommand"
	 * @uml.associationEnd  
	 */
	private FTPCommand _quitCommand;
	/**
	 * @uml.property  name="_database"
	 * @uml.associationEnd  
	 */
	private FTPDatabase _database; 
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_quitCommand = new FTPQuitCommand(_database);
	}

	@Test
	public void testAccept() {
		assertTrue(_quitCommand.accept("QUIT"));
		assertFalse(_quitCommand.accept("DUMB"));
	}

	@Test
	public void testExecute() {
		final String uselessArgument = "useless";
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class); 
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(connection);
		_quitCommand.execute(uselessArgument, clientConfiguration);
		Mockito.verify(_database).getMessage(221);
	}

}
