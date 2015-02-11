package ftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FTPDatabase {

    private static FTPDatabase INSTANCE = null;
    private static final String ACCOUNTS_FILENAME = "_accounts";
    private Map<String, String> _accounts;
    private Map<Integer, String> _answerCodes;

    // COMMANDS
    public static final String USER_COMMAND = "USER";
    public static final String PASS_COMMAND = "PASS";
    public static final String SYST_COMMAND = "SYST";
    public static final String PORT_COMMAND = "PORT";
    public static final String PASV_COMMAND = "PASV";
    public static final String LIST_COMMAND = "LIST";
    public static final String RETR_COMMAND = "RETR";
    public static final String STOR_COMMAND = "STOR";
    public static final String PWD_COMMAND = "PWD";
    public static final String CWD_COMMAND = "CWD";
    public static final String CDUP_COMMAND = "CDUP";
    public static final String QUIT_COMMAND = "QUIT";

    public static final String LOCALHOST_ADDRESS = "127.0.0.1";
    public static final String COMMAND_TOKENS_SEPARATOR = " ";
    public static final char DIRECTORY_SEPARATOR = '/';

    private FTPDatabase() {
	_accounts = new HashMap<String, String>();
	_answerCodes = new HashMap<Integer, String>();
	retrieveAccounts();
	buildReturnCodes();
    }

    public static FTPDatabase getInstance() {
	synchronized (FTPDatabase.class) {
	    if (INSTANCE == null) {
		INSTANCE = new FTPDatabase();
	    }
	}
	return INSTANCE;
    }

    public Map<String, String> getAccounts() {
	return _accounts;
    }

    public String getMessage(int answerCode) {
	return _answerCodes.get(answerCode);
    }

    public void retrieveAccounts() {
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new FileReader(ACCOUNTS_FILENAME));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
		final String[] result = line.split(" ");
		_accounts.put(result[0], result[1]);
	    }
	    reader.close();
	} catch (FileNotFoundException e) {
	    System.err.println("Le fichier n'existe pas");
	} catch (IOException e) {
	    System.err.println("I/O error while reading a line or closing the reader");
	}
    }

    public void buildReturnCodes() {
	_answerCodes.put(200, "PORT command successful.");
	_answerCodes.put(212, "Directory status.");
	_answerCodes.put(213, "File status.");
	_answerCodes.put(215, "Remote system type is UNIX");
	_answerCodes.put(220, "FTP server ready.");
	_answerCodes.put(225, "Data connection open, no transfer in progress.");
	_answerCodes.put(230, "User logged in, proceed.");
	_answerCodes.put(231, "User logged out, service terminated.");
	// _answerCodes.put(257, "PATHNAME\" created");
	_answerCodes.put(331, "Username okay, need password.");
	_answerCodes.put(332, "Need account for login.");
	_answerCodes.put(430, "Invalid username or password.");
	_answerCodes.put(452, "Requested action not taken. File busy.");
	_answerCodes.put(502, "Command not implemented.");
	_answerCodes.put(504, "Command not implemented for that parameter.");
	_answerCodes.put(530, "Not logged in.");
	_answerCodes.put(532, "Need account for storing files in directory {0} . ");
    }

}
