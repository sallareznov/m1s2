package ftp;

/**
 * Class representing an FTP request
 */
public class FTPRequest {

	private String _command;
	private String _argument;
	private int _length;

	/**
	 * constructs a FTP request
	 * @param request the entire request
	 */
	public FTPRequest(String request) {
		final String[] requestTokens = request.split(" ");
		_command = requestTokens[0];
		_argument = (requestTokens.length > 1) ? requestTokens[1] : null;
		_length = requestTokens.length;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return _command;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return _argument;
	}
	
	/**
	 * @return the number of tokens of the request
	 */
	public int getLength() {
		return _length;
	}

}
