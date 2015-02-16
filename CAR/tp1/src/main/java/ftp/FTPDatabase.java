package ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class FTPDatabase {

	private static FTPDatabase INSTANCE = null;
	private Map<String, String> _validAccounts;
	private Map<Integer, String> _codesAndMessages;
	private AtomicInteger _idGenerator;
	private String _localhostIpAddress;
	private String _directorySeparator;
	private Properties _propertiesManager;
	private final String ACCOUNTS_PROPERTIES_FILENAME = "/accounts.properties";
	private final String MESSAGES_PROPERTIES_FILENAME = "/messages.properties";

	private FTPDatabase() {
		_validAccounts = new HashMap<String, String>();
		_codesAndMessages = new HashMap<Integer, String>();
		_idGenerator = new AtomicInteger();
		_propertiesManager = new Properties();
		_directorySeparator = System.getProperty("file.separator");
		_localhostIpAddress = "127.0.0.1";
		retrieveAccounts();
		buildCodesAndMessages();
	}
	
	public static FTPDatabase getInstance() {
		synchronized (FTPDatabase.class) {
			if (INSTANCE == null) {
				INSTANCE = new FTPDatabase();
			}
		}
		return INSTANCE;
	}

	public String getDirectorySeparator() {
		return _directorySeparator;
	}

	public String getLocalhostIpAddress() {
		return _localhostIpAddress;
	}

	public Map<String, String> getAccounts() {
		return _validAccounts;
	}

	public AtomicInteger getIdGenerator() {
		return _idGenerator;
	}

	public String getMessage(int answerCode) {
		return _codesAndMessages.get(answerCode);
	}

	private Set<Entry<Object, Object>> setProperties(String filename) {
		try {
			final InputStream filenameInputStream = FTPDatabase.class.getResourceAsStream(filename);
			_propertiesManager.clear();
			_propertiesManager.load(filenameInputStream);
		} catch (IOException e) {
			System.err
					.println("I/O error while reading a line or closing the reader");
		}
		return _propertiesManager.entrySet();
	}

	private void retrieveAccounts() {
		final Set<Entry<Object, Object>> accounts = setProperties(ACCOUNTS_PROPERTIES_FILENAME);
		for (final Entry<Object, Object> accountEntry : accounts) {
			final String username = (String) accountEntry.getKey();
			final String password = (String) accountEntry.getValue();
			_validAccounts.put(username, password);
		}
	}

	private void buildCodesAndMessages() {
		final Set<Entry<Object, Object>> messages = setProperties(MESSAGES_PROPERTIES_FILENAME);
		for (final Entry<Object, Object> messageEntry : messages) {
			final String returnCodeString = (String) messageEntry.getKey();
			final String message = (String) messageEntry.getValue();
			_codesAndMessages.put(Integer.parseInt(returnCodeString), message);
		}
	}

}
