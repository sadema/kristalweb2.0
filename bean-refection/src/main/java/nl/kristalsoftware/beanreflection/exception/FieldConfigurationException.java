package nl.kristalsoftware.beanreflection.exception;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class FieldConfigurationException extends Exception {

    public FieldConfigurationException(String message) {
        super(message);
    }

    public FieldConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldConfigurationException(Throwable cause) {
        super(cause);
    }

}
