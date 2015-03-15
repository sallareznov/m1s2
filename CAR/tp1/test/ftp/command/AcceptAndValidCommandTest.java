package ftp.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import ftp.server.command.FTPCdupCommand;
import ftp.server.command.FTPCommand;
import ftp.server.command.FTPCwdCommand;
import ftp.server.command.FTPEprtCommand;
import ftp.server.command.FTPEpsvCommand;
import ftp.server.command.FTPListCommand;
import ftp.server.command.FTPNlstCommand;
import ftp.server.command.FTPPassCommand;
import ftp.server.command.FTPPasvCommand;
import ftp.server.command.FTPPortCommand;
import ftp.server.command.FTPPwdCommand;
import ftp.server.command.FTPQuitCommand;
import ftp.server.command.FTPRetrCommand;
import ftp.server.command.FTPStorCommand;
import ftp.server.command.FTPSystCommand;
import ftp.server.command.FTPTypeCommand;
import ftp.server.command.FTPUserCommand;
import ftp.shared.FTPDatabase;
import ftp.shared.FTPRequest;

@RunWith(Parameterized.class)
public class AcceptAndValidCommandTest {

	private FTPCommand ftpCommand;
	private String acceptedCommand;
	private int validLength;

	public AcceptAndValidCommandTest(FTPCommand command,
			String acceptedCommand, int validLength) {
		ftpCommand = command;
		this.acceptedCommand = acceptedCommand;
		this.validLength = validLength;
	}

	@Parameters
	public static Collection<Object[]> data() {
		final FTPDatabase database = Mockito.mock(FTPDatabase.class);
		final Object[][] data = {
			{ new FTPCdupCommand(database), "CDUP", 1 },
			{ new FTPCwdCommand(database), "CWD", 2 },
			{ new FTPEprtCommand(database), "EPRT", 2 },
			{ new FTPEpsvCommand(database), "EPSV", 1 },
			{ new FTPListCommand(database), "LIST", 1 },
			{ new FTPListCommand(database), "LIST", 2 },
			{ new FTPNlstCommand(database), "NLST", 1 },
			{ new FTPNlstCommand(database), "NLST", 2 },
			{ new FTPPassCommand(database), "PASS", 2 },
			{ new FTPPasvCommand(database), "PASV", 1 },
			{ new FTPPortCommand(database), "PORT", 2 },
			{ new FTPPwdCommand(database), "PWD", 1 },
			{ new FTPQuitCommand(database), "QUIT", 1 },
			{ new FTPRetrCommand(database), "RETR", 2 },
			{ new FTPStorCommand(database), "STOR", 2 },
			{ new FTPSystCommand(database), "SYST", 1 },
			{ new FTPTypeCommand(database), "TYPE", 1 },
			{ new FTPUserCommand(database), "USER", 2 },
		};
		return Arrays.asList(data);
	}

	@Test
	public void testAccept() {
		final FTPRequest acceptedRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest declinedRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(acceptedRequest.getCommand()).thenReturn(acceptedCommand);
		Mockito.when(declinedRequest.getCommand()).thenReturn("DUMB");
		assertTrue(ftpCommand.accept(acceptedRequest));
		assertFalse(ftpCommand.accept(declinedRequest));
	}

	@Test
	public void testIsValid() {
		final FTPRequest validRequest = Mockito.mock(FTPRequest.class);
		final FTPRequest invalidRequest = Mockito.mock(FTPRequest.class);
		Mockito.when(validRequest.getLength()).thenReturn(validLength);
		Mockito.when(invalidRequest.getLength()).thenReturn(3);
		assertFalse(ftpCommand.isValid(invalidRequest));
		assertTrue(ftpCommand.isValid(validRequest));
	}

}
