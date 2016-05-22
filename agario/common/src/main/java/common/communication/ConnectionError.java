
package common.communication;

/**
 * Exception to be thrown in case of various connection errors.
 * @author yzsolt
 */
public class ConnectionError extends Exception {
    
    public enum Type {
        CONNECTION_FAIL,
        IOSTREAM_FAIL,
        ACKNOWLEDGMENT_FAIL,
        SERVER_FULL
    }
    
    private final Type type;
    
    /**
     * Constructor
     * @param type Type of error.
     */
    public ConnectionError(Type type) {
        this.type = type;
    }
    
    /**
     * Returns the connection error type.
     * @return Connection error type.
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Generates String based on content.
     * @return String based on error content.
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }
    
}
