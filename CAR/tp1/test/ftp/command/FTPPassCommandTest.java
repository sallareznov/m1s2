package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ftp.FTPDatabase;
import ftp.configuration.FTPClientConfiguration;

@RunWith(MockitoJUnitRunner.class)
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
		assertTrue(_passCommand.accept("PASS"));
		assertFalse(_passCommand.accept("DUMB"));
	}

	@Test
	public void testExecute() {
		final String password = "test";
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class);
		Mockito.when(clientConfiguration.getUsername()).thenReturn("test");
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getConnection()).thenReturn(connection);
		Mockito.when(_accounts.get(Mockito.anyString())).thenReturn(password);
		Mockito.when(_database.getAccounts()).thenReturn(_accounts);
		_passCommand.execute(password, clientConfiguration);
		Mockito.verify(_database).getMessage(225);
		Mockito.verify(_database).getMessage(230);
		Mockito.when(_accounts.get(Mockito.anyString())).thenReturn("DUMB");
		_passCommand.execute(password, clientConfiguration);
		Mockito.verify(_database).getMessage(430);
		Mockito.verify(_database).getMessage(220);
	}

}