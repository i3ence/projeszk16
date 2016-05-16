package communication;

import java.io.Serializable;

/**
 *
 * @author zsiga
 */
public class JoinAcknowledgment implements Serializable, JoinAcknowledgmentInterface {

    private String name;

    public JoinAcknowledgment(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
}
