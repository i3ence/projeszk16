
package client.view;

import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author yzsolt
 */
public class VertexFormatDescriptor {
    
    public class Attribute {
        
        public int type;
        public int componentCount;
        public long offset;
        public boolean isNormalized;
        
        public Attribute(int type, int componentCount, long offset, boolean isNormalized) {
            this.type = type;
            this.componentCount = componentCount;
            this.offset = offset;
            this.isNormalized = isNormalized;
        }
        
        public Attribute(int type, int componentCount, long offset) {
            this(type, componentCount, offset, false);
        }
        
    }
    
    private List<Attribute> m_attributes;
    private long m_size;
    
    public VertexFormatDescriptor() {
        m_attributes = new ArrayList<>();
    }
    
    public void addAttribute(Attribute attribute) {
        
        m_attributes.add(attribute);

        long type_size;

        switch (attribute.type) {

            case GL_HALF_FLOAT:
                    type_size = 2;
                    break;
            case GL_FLOAT:
                    type_size = 4;
                    break;
            case GL_UNSIGNED_BYTE:
                    type_size = 1;
                    break;
            case GL_UNSIGNED_SHORT:
                    type_size = 2;
                    break;
            case GL_UNSIGNED_INT:
                    type_size = 4;
                    break;
            default:
                    throw new UnsupportedOperationException("Unknown GL type.");

        }
        
        m_size += type_size * attribute.componentCount;
        
    }
    
    public void addAttribute(int type, int componentCount, long offset, boolean isNormalized) {
        addAttribute(new Attribute(type, componentCount, offset, isNormalized));
    }
    
    public void addAttribute(int type, int componentCount, long offset) {
        addAttribute(new Attribute(type, componentCount, offset));
    }
    
    public List<Attribute> getAttributes() {
        return m_attributes;
    }
    
    public long getSize() {
        return m_size;
    }
    
}
