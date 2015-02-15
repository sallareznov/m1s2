package ftp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private String _accountsFilename;
	private String _localhostIpAddress;
	private String _directorySeparator;
	private final String CONFIG_PROPERTIES_FILENAME = "conf/config.properties";
	private final String MESSAGES_PROPERTIES_FILENAME = "conf/messages.properties";

	private FTPDatabase() {
		_validAccounts = new HashMap<String, String>();
		_codesAndMessages = new HashMap<Integer, String>();
		_idGenerator = new AtomicInteger();
		_directorySeparator = System.getProperty("file.separator");
		setProperties();
		retrieveAccounts();
		buildCodesAndMessages();
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
		return _codesAndMessages.get(answerCode);
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
			System.err.println("ERROR while opening or loading the file");
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

	private void buildCodesAndMessages() {
		try {
			final Properties messageProperties = new Properties();
			final InputStream messagesInputStream = new FileInputStream(MESSAGES_PROPERTIES_FILENAME);
			messageProperties.load(messagesInputStream);
			final Set<Entry<Object, Object>> messages = messageProperties.entrySet();
			for (final Entry<Object, Object> messageEntry : messages) {
				_codesAndMessages.put((Integer) messageEntry.getKey(), (String) messageEntry.getValue());
			}
		} catch (IOException e) {
			System.err.println("ERROR while opening or loading the file");
		}
	}

}
