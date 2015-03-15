package ftp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ftp.command.FTPPassCommandTest;
import ftp.command.FTPPwdCommandTest;
import ftp.command.FTPQuitCommandTest;
import ftp.command.FTPSystCommandTest;
import ftp.command.FTPTypeCommandTest;
import ftp.command.FTPUserCommandTest;

@RunWith(Suite.class)
@SuiteClasses({ FTPUserCommandTest.class, FTPPassCommandTest.class,
		FTPSystCommandTest.class, FTPPwdCommandTest.class,
		FTPTypeCommandTest.class, FTPQuitCommandTest.class })
public class MasterTester {
}
