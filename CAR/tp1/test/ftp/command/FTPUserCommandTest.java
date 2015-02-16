package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ftp.FTPDatabase;
import ftp.configuration.FTPClientConfiguration;

public class FTPUserCommandTest {
	
	private FTPCommand _userCommand;
	private FTPDatabase _database; 
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_userCommand = new FTPUserCommand(_database);
	}

	@Test
	public void testAccept() {
		assertTrue(_userCommand.accept("USER"));
		assertFalse(_userCommand.accept("DUMB"));
	}

	@Test
	public void testExecute() {
		final String username = "anonymous";
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class); 
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getConnection()).thenReturn(connection);
		_userCommand.execute(username, clientConfiguration);
		Mockito.verify(clientConfiguration).setUsername(username);
		Mockito.verify(_database).getMessage(Mockito.anyInt());
	}

}
