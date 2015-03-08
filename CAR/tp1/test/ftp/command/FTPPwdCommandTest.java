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
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import ftp.server.command.FTPCommand;
import ftp.server.command.FTPPwdCommand;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * @author diagne
 */
@PrepareForTest(MessageFormat.class)
public class FTPPwdCommandTest {

	private FTPCommand _pwdCommand;
	private FTPDatabase _database;

	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_pwdCommand = new FTPPwdCommand(_database);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("PWD");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_pwdCommand.accept(acceptedRequest));
		assertFalse(_pwdCommand.accept(declinedRequest));
	}
	
	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(2);
		Mockito.when(validRequest.getLength()).thenReturn(1);
		assertFalse(_pwdCommand.isValid(invalidRequest));
		assertTrue(_pwdCommand.isValid(validRequest));
	}

	@Test
	@Ignore
	public void testExecute() throws IOException {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		final String workingDirectory = "home/m1/someGuy";
		final FTPClientConfiguration clientConfiguration = Mockito
				.mock(FTPClientConfiguration.class);
		Mockito.when(clientConfiguration.getWorkingDirectory()).thenReturn(
				workingDirectory);
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		PowerMockito.mockStatic(MessageFormat.class);
		Mockito.when(
				MessageFormat.format(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(workingDirectory);
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(
				connection);
		_pwdCommand.execute(request, clientConfiguration);
		Mockito.verify(_database).getMessage(257);
	}

}
