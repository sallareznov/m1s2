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

import ftp.shared.FTPClientConfiguration;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
public class FTPCdupCommandTest {
	
	private FTPCommand cdupCommand;
	private FTPDatabase database;
	@Mock
	private Map<String, String> accounts;
	
	@Before
	public void setUp() {
		database = Mockito.mock(FTPDatabase.class);
		cdupCommand = new FTPCdupCommand(database);
	}

	@Test
	public void testExecute() throws IOException {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		Mockito.when(request.getLength()).thenReturn(1);
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class);
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
		Mockito.when(clientConfiguration.getBaseDirectory()).thenReturn("repo");
		Mockito.when(clientConfiguration.getWorkingDirectory()).thenReturn("repo/test");
		Mockito.when(clientConfiguration.isConnected()).thenReturn(true);
		Mockito.when(database.getMessage(200)).thenReturn("");
		cdupCommand.execute(request, clientConfiguration);
		Mockito.verify(database).getMessage(200);
	}

}