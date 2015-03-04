package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import ftp.FTPDatabase;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

/**
 * @author  diagne
 */
public class FTPTypeCommandTest {

	private FTPCommand _typeCommand;
	private FTPDatabase _database;

	@Before
	public void setUp() throws Exception {
		_database = Mockito.mock(FTPDatabase.class);
		_typeCommand = new FTPTypeCommand(_database);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("TYPE");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_typeCommand.accept(acceptedRequest));
		assertFalse(_typeCommand.accept(declinedRequest));
	}
	
	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(2);
		Mockito.when(validRequest.getLength()).thenReturn(1);
		assertFalse(_typeCommand.isValid(invalidRequest));
		assertTrue(_typeCommand.isValid(validRequest));
	}

	@Test
	@Ignore
	public void testExecute() {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		final FTPClientConfiguration clientConfiguration = Mockito
				.mock(FTPClientConfiguration.class);
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getCommandSocket())
				.thenReturn(connection);
		_typeCommand.execute(request, clientConfiguration);
		Mockito.verify(_database).getMessage(200);
	}

}
