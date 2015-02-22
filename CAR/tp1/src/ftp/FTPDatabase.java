package ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Class representing a database
 */
public class FTPDatabase {

	private Map<String, String> _validAccounts;
	private Map<Integer, String> _codesAndMessages;
	private String _hostname;
	private Properties _propertiesManager;
	private final String ACCOUNTS_PROPERTIES_FILENAME = "conf/accounts.properties";
	private final String MESSAGES_PROPERTIES_FILENAME = "conf/messages.properties";

	/**
	 * constructs the database
	 */
	public FTPDatabase() {
		_validAccounts = new HashMap<String, String>();
		_codesAndMessages = new HashMap<Integer, String>();
		_propertiesManager = new Properties();
		_hostname = "localhost";
		retrieveAccounts();
		buildCodesAndMessages();
	}
	
	public String getHostname() {
		return _hostname;
	}

	public Map<String, String> getAccounts() {
		return _validAccounts;
	}
	
	/**
	 * @param answerCode
	 * @return the message associated to the answer code
	 */
	public String getMessage(int answerCode) {
		return _codesAndMessages.get(answerCode);
	}

	private Set<Entry<Object, Object>> setProperties(String filename) {
		try {
			final InputStream filenameInputStream = new FileInputStream(filename);
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
