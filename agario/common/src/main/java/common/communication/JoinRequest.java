
package common.communication;

import java.io.Serializable;

/**
 * A player's request to join, from the client to the server.
 */
public class JoinRequest implements Serializable {

    private final String name;

    public JoinRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
