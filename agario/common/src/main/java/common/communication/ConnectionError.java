
package common.communication;

/**
 *
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
    
    public ConnectionError(Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        // TODO
        return "";
    }
    
}
