package exception;

public class PincodeLimitExceededException extends RuntimeException {
    public PincodeLimitExceededException(String message) {
        super(message);
    }
}
