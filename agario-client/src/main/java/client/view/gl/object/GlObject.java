
package client.view.gl.object;

/**
 * Parent class for OpenGL objects.
 * @author yzsolt
 */
abstract public class GlObject {
    
    protected int m_id;
    protected int m_gl_type;
    
    protected GlObject(int type) {
        m_gl_type = type;
    }
    
    public int getId() {
        return m_id;
    }
    
    abstract protected void delete();
    
}
