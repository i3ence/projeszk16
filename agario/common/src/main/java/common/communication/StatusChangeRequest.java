
package common.communication;

import java.io.Serializable;

/**
 *
 */
public class StatusChangeRequest implements Request, Serializable {
    
    public enum Status {
        IN_MENU,
        IN_GAME,
        REANIMATE,
        QUIT
    }
    
    private final Status status;

    /**
     *
     * @param status
     */
    public StatusChangeRequest(Status status) {
        this.status = status;
    }
    
    /**
     * Returns the requested status.
     * @return The status of the client.
     */
    public Status getStatus() {
        return status;
    }

}
