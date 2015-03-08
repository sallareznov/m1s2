package ftp.shared;

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

	private Map<String, String> validAccounts;
	private Map<Integer, String> codesAndMessages;
	private String hostname;
	private Properties propertiesManager;
	private static final String ACCOUNTS_PROPERTIES_FILENAME = "conf/accounts.properties";
	private static final String MESSAGES_PROPERTIES_FILENAME = "conf/messages.properties";

	/**
	 * constructs the database
	 * @throws IOException 
	 */
	public FTPDatabase() throws IOException {
		validAccounts = new HashMap<String, String>();
		codesAndMessages = new HashMap<Integer, String>();
		propertiesManager = new Properties();
		hostname = "localhost";
		retrieveAccounts();
		buildCodesAndMessages();
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return the accounts
	 */
	public Map<String, String> getAccounts() {
		return validAccounts;
	}

	/**
	 * @param answerCode
	 * @return the message associated to the answer code
	 */
	public String getMessage(int answerCode) {
		return codesAndMessages.get(answerCode);
	}

	private Set<Entry<Object, Object>> setProperties(String filename) throws IOException {
		final InputStream filenameInputStream = new FileInputStream(filename);
		propertiesManager.clear();
		propertiesManager.load(filenameInputStream);
		return propertiesManager.entrySet();
	}

	private void retrieveAccounts() throws IOException {
		final Set<Entry<Object, Object>> accounts = setProperties(ACCOUNTS_PROPERTIES_FILENAME);
		for (final Entry<Object, Object> accountEntry : accounts) {
			final String username = (String) accountEntry.getKey();
			final String password = (String) accountEntry.getValue();
			validAccounts.put(username, password);
		}
	}

	private void buildCodesAndMessages() throws IOException {
		final Set<Entry<Object, Object>> messages = setProperties(MESSAGES_PROPERTIES_FILENAME);
		for (final Entry<Object, Object> messageEntry : messages) {
			final String returnCodeString = (String) messageEntry.getKey();
			final String message = (String) messageEntry.getValue();
			codesAndMessages.put(Integer.parseInt(returnCodeString), message);
		}
	}

}
