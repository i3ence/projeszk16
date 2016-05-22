
package client;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import org.joml.Vector3f;

/**
 * Various utility functions for the client.
 * @author yzsolt
 */
public class Util {
    
    public static String getFileContents(String path) throws IOException {
        
        List<String> lines = Files.readAllLines(new File(path).toPath());
        StringBuilder file_content = new StringBuilder();
        
        for (String line : lines) {
            file_content.append(line);
            file_content.append('\n');
        }
        
        return file_content.toString();
        
    }
    
    public static String byteBufferToString(ByteBuffer byte_buffer) {
        
        byte[] byte_array = new byte[byte_buffer.limit()];
        byte_buffer.get(byte_array);
        
        return new String(byte_array, Charset.forName("UTF-8"));
        
    }

    public static Vector3f convertColor(Color color) {
        return new Vector3f(color.getRed(), color.getGreen(), color.getBlue());
    }
    
}
