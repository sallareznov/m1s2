package ftp.server.command;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.MessageFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ftp.server.command.FTPCommand;
import ftp.server.command.FTPTypeCommand;
import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
public class FTPTypeCommandTest {

	private FTPCommand typeCommand;
	private FTPDatabase database;

	@Before
	public void setUp() throws Exception {
		database = Mockito.mock(FTPDatabase.class);
		typeCommand = new FTPTypeCommand(database);
	}

	@Test
	public void testExecute() throws IOException {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		Mockito.when(request.getLength()).thenReturn(1);
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
		Mockito.when(
				MessageFormat.format(Mockito.anyString(), Mockito.anyObject()))
				.thenReturn("");
		Mockito.when(clientConfiguration.getCommandSocket()).thenReturn(
				connection);
		Mockito.when(database.getMessage(200)).thenReturn("");
		Mockito.when(clientConfiguration.isConnected()).thenReturn(true);
		typeCommand.execute(request, clientConfiguration);
		Mockito.verify(database).getMessage(200);
	}

}
