package ftp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class FTPDatabase {

	private static FTPDatabase INSTANCE = null;
	private Map<String, String> _validAccounts;
	private Map<Integer, String> _answerCodes;
	private AtomicInteger _idGenerator;
	private String _accountsFilename;
	private final String CONFIG_PROPERTIES_FILENAME = "config.properties";
	private String _localhostIpAddress;
	private String _directorySeparator;

	private FTPDatabase() {
		_validAccounts = new HashMap<String, String>();
		_answerCodes = new HashMap<Integer, String>();
		_idGenerator = new AtomicInteger();
		_directorySeparator = System.getProperty("file.separator");
		setProperties();
		retrieveAccounts();
		buildReturnCodes();
	}
	
	public String getDirectorySeparator() {
		return _directorySeparator;
	}

	public String getLocalhostIpAddress() {
		return _localhostIpAddress;
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
		return _validAccounts;
	}
	
	public AtomicInteger getIdGenerator() {
		return _idGenerator;
	}

	public String getMessage(int answerCode) {
		return _answerCodes.get(answerCode);
	}
	
	private void setProperties() {
		try {
			final Properties configProperties = new Properties();
			final InputStream propertiesInputStream = new FileInputStream(CONFIG_PROPERTIES_FILENAME);
			configProperties.load(propertiesInputStream);
			_accountsFilename = configProperties.getProperty("accountsFilename");
			_localhostIpAddress = configProperties.getProperty("localhostIpAddress");
			propertiesInputStream.close();
		}
		catch (IOException e) {
			
		}
	}

	private void retrieveAccounts() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(_accountsFilename));
			String line = null;
			while ((line = reader.readLine()) != null) {
				final String[] result = line.split(" ");
				_validAccounts.put(result[0], result[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier n'existe pas");
		} catch (IOException e) {
			System.err
					.println("I/O error while reading a line or closing the reader");
		}
	}

	private void buildReturnCodes() {
		_answerCodes.put(125, "Data connection already opened, transfer starting.");
		_answerCodes.put(150, "File status okay, about to open data connection.");
		_answerCodes.put(200, "{0} command successful.");
		_answerCodes.put(212, "Directory status.");
		_answerCodes.put(213, "File status.");
		_answerCodes.put(215, "Remote system type is UNIX");
		_answerCodes.put(220, "FTP server ready.");
		_answerCodes.put(225, "Data connection open, no transfer in progress.");
		_answerCodes.put(226, "Closing data connection, requested file action successful");
		_answerCodes.put(227, "={0}, {1}, {2}, {3}, {4}, {5}");
		_answerCodes.put(230, "User logged in, proceed.");
		_answerCodes.put(231, "User logged out, service terminated.");
		_answerCodes.put(257, "{0}");
		_answerCodes.put(331, "Username okay, need password.");
		_answerCodes.put(332, "Need account for login.");
		_answerCodes.put(430, "Invalid username or password.");
		_answerCodes.put(452, "Requested action not taken. File busy.");
		_answerCodes.put(500, "Command failed to execute.");
		_answerCodes.put(502, "Command not implemented.");
		_answerCodes.put(504, "Command not implemented for that parameter.");
		_answerCodes.put(530, "Not logged in.");
		_answerCodes.put(532,
				"Need account for storing files in directory {0} . ");
		_answerCodes.put(550, "Requested action not taken. File unavailable");
	}

}
