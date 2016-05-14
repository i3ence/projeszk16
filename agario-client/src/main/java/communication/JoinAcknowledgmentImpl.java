package communication;

/**
 *
 * @author zsiga
 */
public class JoinAcknowledgmentImpl implements JoinAcknowledgment{

    private String name;

    public JoinAcknowledgmentImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
