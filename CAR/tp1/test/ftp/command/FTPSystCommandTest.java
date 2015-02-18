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
public class FTPSystCommandTest {

	/**
	 * @uml.property  name="_systCommand"
	 * @uml.associationEnd  
	 */
	private FTPCommand _systCommand;
	/**
	 * @uml.property  name="_database"
	 * @uml.associationEnd  
	 */
	private FTPDatabase _database; 
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_systCommand = new FTPSystCommand(_database);
	}

	@Test
	public void testAccept() {
		assertTrue(_systCommand.accept("SYST"));
		assertFalse(_systCommand.accept("DUMB"));
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
		Mockito.when(clientConfiguration.getConnection()).thenReturn(connection);
		_systCommand.execute(uselessArgument, clientConfiguration);
		Mockito.verify(_database).getMessage(215);
	}

}
