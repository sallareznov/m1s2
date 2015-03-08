package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ftp.server.command.FTPCommand;
import ftp.server.command.FTPPassCommand;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

/**
 * @author  diagne
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
public class FTPPassCommandTest {
	
	private FTPCommand _passCommand;
	private FTPDatabase _database;
	@Mock
	private Map<String, String> _accounts;
	
	@Before
	public void setUp() {
		_database = Mockito.mock(FTPDatabase.class);
		_passCommand = new FTPPassCommand(_database);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn("PASS");
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(_passCommand.accept(acceptedRequest));
		assertFalse(_passCommand.accept(declinedRequest));
	}

	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(invalidRequest.getLength()).thenReturn(1);
		Mockito.when(validRequest.getLength()).thenReturn(2);
		assertFalse(_passCommand.isValid(invalidRequest));
		assertTrue(_passCommand.isValid(validRequest));
	}

	@Ignore
	@Test
	public void testExecute() throws IOException {
		final FTPRequest correctRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(correctRequest.getArgument()).thenReturn("test");
		final FTPRequest incorrectRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(incorrectRequest.getArgument()).thenReturn("incorrect");
		final String correctPassword = correctRequest.getArgument();
		final String incorrectPassword = incorrectRequest.getArgument();
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class);
		Mockito.when(clientConfiguration.getUsername()).thenReturn("test");
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		PowerMockito.mockStatic(MessageFormat.class);
		Mockito.when(MessageFormat.format(Mockito.anyString(), Mockito.anyString())).thenReturn("");
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(connection);
		Mockito.when(_accounts.get(Mockito.anyString())).thenReturn(correctPassword);
		Mockito.when(_database.getAccounts()).thenReturn(_accounts);
		Mockito.when(_database.getMessage(230)).thenReturn("");
		Mockito.when(_database.getMessage(332)).thenReturn("");
		_passCommand.execute(correctRequest, clientConfiguration);
		Mockito.verify(_database).getMessage(230);
		Mockito.when(_accounts.get(Mockito.anyString())).thenReturn(incorrectPassword);
		_passCommand.execute(correctRequest, clientConfiguration);
		Mockito.verify(_database).getMessage(332);
	}

}