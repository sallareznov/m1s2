package ftp.server.command;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Map;

import org.junit.Before;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
public class FTPPassCommandTest {
	
	private FTPCommand passCommand;
	private FTPDatabase database;
	@Mock
	private Map<String, String> accounts;
	
	@Before
	public void setUp() {
		database = Mockito.mock(FTPDatabase.class);
		passCommand = new FTPPassCommand(database);
	}

	@Test
	public void testExecute() throws IOException {
		final FTPRequest correctRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(correctRequest.getArgument()).thenReturn("test");
		Mockito.when(correctRequest.getLength()).thenReturn(2);
		final FTPRequest incorrectRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(incorrectRequest.getArgument()).thenReturn("incorrect");
		Mockito.when(incorrectRequest.getLength()).thenReturn(2);
		final String correctPassword = correctRequest.getArgument();
		final String username = "test";
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class);
		Mockito.when(clientConfiguration.getUsername()).thenReturn(username);
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		PowerMockito.mockStatic(MessageFormat.class);
		Mockito.when(
				MessageFormat.format(Mockito.anyString(), Mockito.anyObject()))
				.thenReturn("");
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(connection);
		Mockito.when(accounts.get(username)).thenReturn(correctPassword);
		Mockito.when(database.getAccounts()).thenReturn(accounts);
		Mockito.when(database.getMessage(230)).thenReturn("");
		Mockito.when(database.getMessage(332)).thenReturn("");
		passCommand.execute(correctRequest, clientConfiguration);
		Mockito.verify(database).getMessage(230);
		Mockito.verify(clientConfiguration).setConnected(true);
		passCommand.execute(incorrectRequest, clientConfiguration);
		Mockito.verify(database).getMessage(332);
	}

}