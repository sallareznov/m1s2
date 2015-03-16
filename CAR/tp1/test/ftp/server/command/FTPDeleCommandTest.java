package ftp.server.command;

import static org.junit.Assert.fail;

import java.io.File;
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
public class FTPDeleCommandTest {
	
	private FTPCommand deleCommand;
	private FTPDatabase database;
	@Mock
	private Map<String, String> accounts;
	
	@Before
	public void setUp() {
		database = Mockito.mock(FTPDatabase.class);
		deleCommand = new FTPDeleCommand(database);
	}
	
	private String createTestFile() throws IOException {
		final String directory = new File("repo").getAbsolutePath();
		final String createdFile = directory + System.getProperty("file.separator") + "fileForTest";
		final File file = new File(createdFile);
		file.createNewFile();
		return file.getName();
	}

	@Test
	public void testExecute() throws IOException {
		final FTPRequest request = Mockito.mock(FTPRequest.class);
		Mockito.when(request.getLength()).thenReturn(2);
		Mockito.when(request.getArgument()).thenReturn(createTestFile());
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
		Mockito.when(clientConfiguration.getWorkingDirectory()).thenReturn("repo");
		Mockito.when(clientConfiguration.getDirectorySeparator()).thenReturn(System.getProperty("file.separator"));
		Mockito.when(clientConfiguration.isConnected()).thenReturn(true);
		Mockito.when(database.getMessage(250)).thenReturn("");
		Mockito.when(database.getMessage(550)).thenReturn("");
		deleCommand.execute(request, clientConfiguration);
		Mockito.verify(database).getMessage(250);
		Mockito.when(request.getArgument()).thenReturn("dumbfile.c");
		deleCommand.execute(request, clientConfiguration);
		Mockito.verify(database).getMessage(550);
	}

}