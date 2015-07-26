package nl.kristalsoftware.kristalweb.exception;

/**
 * Created by sjoerdadema on 11-07-15.
 */
public class AppRepositoryException extends Exception {
    public AppRepositoryException() {
        super();
    }

    public AppRepositoryException(String message) {
        super(message);
    }

    public AppRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRepositoryException(Throwable cause) {
        super(cause);
    }

    protected AppRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
