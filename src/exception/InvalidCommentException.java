package exception;

public class InvalidCommentException extends RuntimeException {
    public InvalidCommentException(String message) {
        super(message);
    }
}
