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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ftp.FTPDatabase;
import ftp.configuration.FTPClientConfiguration;

/**
 * @author  diagne
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageFormat.class)
public class FTPPassCommandTest {
	
	/**
	 * @uml.property  name="_passCommand"
	 * @uml.associationEnd  
	 */
	private FTPCommand _passCommand;
	/**
	 * @uml.property  name="_database"
	 * @uml.associationEnd  
	 */
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
		final String correctPassword = "test";
		final String incorrectPassword = "incorrect";
		final FTPClientConfiguration clientConfiguration = Mockito.mock(FTPClientConfiguration.class);
		Mockito.when(clientConfiguration.getUsername()).thenReturn(correctPassword);
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
		_passCommand.execute(correctPassword, clientConfiguration);
		Mockito.verify(_database).getMessage(230);
		Mockito.when(_accounts.get(Mockito.anyString())).thenReturn(incorrectPassword);
		_passCommand.execute(correctPassword, clientConfiguration);
		Mockito.verify(_database).getMessage(332);
	}

}