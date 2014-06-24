
public class LoopFoundException extends Exception {

	public LoopFoundException() {
	}
	
	public LoopFoundException(String message) {
		super(message);
	}

	public LoopFoundException(Throwable cause) {
		super(cause);
	}

	public LoopFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
