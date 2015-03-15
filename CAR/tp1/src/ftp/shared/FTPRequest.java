package ftp.shared;

/**
 * Class representing an FTP request
 */
public class FTPRequest {

	private String command;
	private String argument;
	private int length;

	/**
	 * constructs a FTP request
	 * 
	 * @param request
	 *            the entire request
	 */
	public FTPRequest(String request) {
		final String[] requestTokens = request.split(" ");
		command = requestTokens[0];
		argument = (requestTokens.length > 1) ? requestTokens[1] : null;
		length = requestTokens.length;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * @return the number of tokens of the request
	 */
	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		if (argument == null) {
			return command;
		}
		return command + " " + argument;
	}

}
