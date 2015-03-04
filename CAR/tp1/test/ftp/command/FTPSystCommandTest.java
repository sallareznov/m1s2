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
public class FTPSystCommandTest {

	private FTPCommand _systCommand;
	private FTPDatabase _database; 
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_systCommand = new FTPSystCommand(_database);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("SYST");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_systCommand.accept(acceptedRequest));
		assertFalse(_systCommand.accept(declinedRequest));
	}
	
	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(2);
		Mockito.when(validRequest.getLength()).thenReturn(1);
		assertFalse(_systCommand.isValid(invalidRequest));
		assertTrue(_systCommand.isValid(validRequest));
	}

	@Ignore
	@Test
	public void testExecute() {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class); 
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(connection);
		_systCommand.execute(request, clientConfiguration);
		Mockito.verify(_database).getMessage(215);
	}

}
