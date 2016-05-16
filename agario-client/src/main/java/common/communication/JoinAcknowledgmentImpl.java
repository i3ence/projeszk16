package common.communication;

import java.io.Serializable;

/**
 *
 * @author zsiga
 */
public class JoinAcknowledgmentImpl implements Serializable, JoinAcknowledgment {

    private String name;

    public JoinAcknowledgmentImpl(String name) {
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
