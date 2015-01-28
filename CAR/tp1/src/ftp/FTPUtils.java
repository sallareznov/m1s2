package ftp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FTPUtils {
	
	private static final String ACCOUNTS_FILENAME = "_accounts";
	private Map<String, String> _accounts;
	private Map<Integer, String> _answerCodes;
	
	public FTPUtils() throws IOException {
		_accounts = new HashMap<String, String>();
		_answerCodes = new HashMap<Integer, String>();
		retrieveAccounts();
		buildReturnCodes();
	}
	
	public Map<String, String> getAccounts() {
		return _accounts;
	}
	
	public String getMessage(int answerCode) {
		return _answerCodes.get(answerCode);
	}
	
	public void retrieveAccounts() throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(ACCOUNTS_FILENAME));
			String line = null;
			while ((line = reader.readLine()) != null) {
				final String[] result = line.split(" ");
				_accounts.put(result[0], result[1]);
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Le fichier n'existe pas");
		}
		finally {
			reader.close();
		}
	}
	
	public void buildReturnCodes() {
		_answerCodes.put(530, "Not logged in.");
		_answerCodes.put(504, "Command not implemented for that parameter.");
		_answerCodes.put(502, "Command not implemented.");
		_answerCodes.put(532, "Need account for storing files.");
		_answerCodes.put(212, "Directory status.");
		_answerCodes.put(213, "File status.");
		_answerCodes.put(550, "Requested action not taken. No access.");
		_answerCodes.put(452, "Requested action not taken. File busy.");
		_answerCodes.put(220, "FTP server ready.");
		_answerCodes.put(331, "Username okay, need password.");
		_answerCodes.put(332, "Need account for login.");
		_answerCodes.put(430, "Invalid username or password.");
	}

}
