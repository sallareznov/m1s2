package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ftp.FTPDatabase;
import ftp.FTPRequest;
import ftp.configuration.FTPClientConfiguration;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
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
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("USER");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_userCommand.accept(acceptedRequest));
		assertFalse(_userCommand.accept(declinedRequest));
	}

	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(1);
		Mockito.when(validRequest.getLength()).thenReturn(2);
		assertFalse(_userCommand.isValid(invalidRequest));
		assertTrue(_userCommand.isValid(validRequest));
	}

	@Ignore
	@Test
	public void testExecute() {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		Mockito.when(request.getArgument()).thenReturn("test");
		final FTPClientConfiguration clientConfiguration = Mockito
				.mock(FTPClientConfiguration.class);
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		PowerMockito.mockStatic(MessageFormat.class);
		PowerMockito.when(MessageFormat.format(Mockito.anyString(), Mockito.anyObject())).thenReturn("");
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(
				connection);
		_userCommand.execute(request, clientConfiguration);
		PowerMockito.verifyStatic();
		Mockito.verify(clientConfiguration).setUsername(request.getArgument());
		Mockito.verify(_database).getMessage(331);
	}

}
