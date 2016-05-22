package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class UtilTest {
    
    /**
     * Test of getFileContents method, of class Util
     * with standard two-line file
     */
    @Test
    public void testGetFileContents1() throws Exception {
        String path = "testfile.txt";
        PrintWriter writer = new PrintWriter(path);
        writer.println("Elso sor");
        writer.println("Masodik sor");
        writer.close();
        String result = Util.getFileContents(path);
        assertEquals("Elso sor\nMasodik sor\n", result);
        File file = new File(path);
        file.delete();
    }

    /**
     * Test of byteBufferToString method, of class Util.
     */
    @Test
    public void testByteBufferToString() {
        byte[] bytes = "StringToBytes".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        String result = Util.byteBufferToString(buffer);
        assertEquals("StringToBytes", result);
    }
}
