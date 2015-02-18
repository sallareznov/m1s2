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
@SuiteClasses(value={
FTPUserCommandTest.class,
FTPPassCommandTest.class,
FTPPwdCommandTest.class,
FTPQuitCommandTest.class,
FTPSystCommandTest.class,
FTPTypeCommandTest.class
})
public class FTPCommandsTest {}
