package ftp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ftp.server.command.FTPAcceptAndValidCommandTest;
import ftp.server.command.FTPPassCommandTest;
import ftp.server.command.FTPPwdCommandTest;
import ftp.server.command.FTPQuitCommandTest;
import ftp.server.command.FTPSystCommandTest;
import ftp.server.command.FTPTypeCommandTest;
import ftp.server.command.FTPUserCommandTest;
import ftp.shared.FTPClientConfigurationTest;

@RunWith(Suite.class)
@SuiteClasses({ FTPAcceptAndValidCommandTest.class, FTPUserCommandTest.class, FTPPassCommandTest.class,
		FTPSystCommandTest.class, FTPPwdCommandTest.class,
		FTPTypeCommandTest.class, FTPQuitCommandTest.class,
		FTPClientConfigurationTest.class})
public class FTPMasterTester {
}
