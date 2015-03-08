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

import ftp.server.command.FTPCommand;
import ftp.server.command.FTPQuitCommand;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * @author  diagne
 */
public class FTPQuitCommandTest {

	private FTPCommand _quitCommand;
	private FTPDatabase _database; 
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_quitCommand = new FTPQuitCommand(_database);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("QUIT");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_quitCommand.accept(acceptedRequest));
		assertFalse(_quitCommand.accept(declinedRequest));
	}
	
	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(2);
		Mockito.when(validRequest.getLength()).thenReturn(1);
		assertFalse(_quitCommand.isValid(invalidRequest));
		assertTrue(_quitCommand.isValid(validRequest));
	}

	@Ignore
	@Test
	public void testExecute() throws IOException {
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
		_quitCommand.execute(request, clientConfiguration);
		Mockito.verify(_database).getMessage(221);
	}

}
