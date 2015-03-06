package bases.util;

public class NonExistentPairingException extends Exception {

	private static final long serialVersionUID = 4985973374579137000L;
	
	public NonExistentPairingException(char base) {
		super("No pairing found for : " + base);
	}

	public NonExistentPairingException(NonExistentPairingException e) {
		super(e);
	}

}
