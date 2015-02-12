package ftp;

public class FTPRequest {
    
    private String _command;
    private String _argument;
    
    public FTPRequest(String request) {
	final String[] requestTokens = request.split(" ");
	_command = requestTokens[0];
	_argument = (requestTokens.length > 1) ? requestTokens[1] : null;
    }
    
    public String getCommand() {
	return _command;
    }
    
    public String getArgument() {
	return _argument;
    }

}
