package ftp.shared;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ftp.server.util.FailedCwdException;

public class FTPClientConfigurationTest {

	private FTPClientConfiguration clientConfiguration;

	@Before
	public void setUp() {
		final FTPServerConfiguration serverConfiguration = Mockito
				.mock(FTPServerConfiguration.class);
		final AtomicInteger idGenerator = Mockito.mock(AtomicInteger.class);
		Mockito.when(serverConfiguration.getBaseDirectory()).thenReturn("repo");
		Mockito.when(serverConfiguration.getDirectorySeparator()).thenReturn(
				System.getProperty("file.separator"));
		Mockito.when(serverConfiguration.getIdGenerator()).thenReturn(idGenerator);
		clientConfiguration = new FTPClientConfiguration(serverConfiguration);
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testGoDownWithException() throws FileNotFoundException {
		assertEquals("repo", clientConfiguration.getWorkingDirectory());
		clientConfiguration.goDown("dumb");
	}
	
	@Test
	public void testGoDownAndGoUp() throws FileNotFoundException, FailedCwdException {
		assertEquals("repo", clientConfiguration.getWorkingDirectory());
		clientConfiguration.goDown("test");
		assertEquals("repo/test", clientConfiguration.getWorkingDirectory());
		clientConfiguration.goUp();
		assertEquals("repo/", clientConfiguration.getWorkingDirectory());
	}
	
	@Test(expected=FailedCwdException.class)
	public void testGoUpWithException() throws FailedCwdException {
		assertEquals("repo", clientConfiguration.getWorkingDirectory());
		clientConfiguration.goUp();
	}

}
