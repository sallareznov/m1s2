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

import ftp.FTPDatabase;
import ftp.configuration.FTPClientConfiguration;

public class FTPTypeCommandTest {

	private FTPCommand _typeCommand;
	private FTPDatabase _database;

	@Before
	public void setUp() throws Exception {
		_database = Mockito.mock(FTPDatabase.class);
		_typeCommand = new FTPTypeCommand(_database);
	}

	@Test
	public void testAccept() {
		assertTrue(_typeCommand.accept("TYPE"));
		assertFalse(_typeCommand.accept("TYPE"));
	}

	@Test
	@Ignore
	public void testExecute() {
		final String uselessArgument = "useless";
		final FTPClientConfiguration clientConfiguration = Mockito
				.mock(FTPClientConfiguration.class);
		final Socket connection = Mockito.mock(Socket.class);
		final OutputStream outputStream = Mockito.mock(OutputStream.class);
		try {
			Mockito.when(connection.getOutputStream()).thenReturn(outputStream);
		} catch (IOException e) {
			fail();
		}
		Mockito.when(clientConfiguration.getConnection())
				.thenReturn(connection);
		_typeCommand.execute(uselessArgument, clientConfiguration);
		Mockito.verify(_database).getMessage(200);
	}

}
