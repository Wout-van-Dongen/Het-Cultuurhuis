package be.vdab.cultuurhuis.utils;

public class RequestNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public RequestNotFoundException() {
		super();
	}
	
	public RequestNotFoundException(String message) {
		super(message);
	}
	
	public RequestNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public RequestNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
