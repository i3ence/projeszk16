
package common.communication;

import java.io.Serializable;

/**
 * 
 * @author zsiga
 * @author zoli-
 * @author yzsolt
 */
public class JoinAcknowledgment implements Serializable {

    private String name;

    public JoinAcknowledgment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
