/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hegym
 */
public class UtilTest {
    /**
     * Test of clamp method, of class Util:
     * value less than min
     */
    @Test
    public void testClamp1() {
        float result = Util.clamp(1, 2, 3);
        assertEquals(2.0F, result, 0.0);
    }
    
    /**
     * Test of clamp method, of class Util:
     * value more than max
     */
    @Test
    public void testClamp2() {
        float result = Util.clamp(10, 2, 3);
        assertEquals(3, result, 0.0);
    }
    
    /**
     * Test of clamp method, of class Util:
     * return value
     */
    @Test
    public void testClamp3() {
        float result = Util.clamp(2.5F, -5, 3);
        assertEquals(2.5F, result, 0.0);
    }
    
    
    /**
     * Test of clamp with radius method, of class Util:
     * value less than min
     */
    @Test
    public void testClampWithRadius1() {
        float result = Util.clampWithRadius(1, 5, 8, 2);
        assertEquals(7.f, result, 0.f);
    }
    
    /**
     * Test of clamp with radius method, of class Util:
     * value more than max
     */
    @Test
    public void testClampWithRadius2() {
        float result = Util.clampWithRadius(10, 2, 7, 5);
        assertEquals(2.f, result, 0.f);
    }
    
    /**
     * Test of clamp with radius method, of class Util:
     * return value
     */
    @Test
    public void testClampWithRadius3() {
        float result = Util.clampWithRadius(2.5F, -5, 8, 5);
        assertEquals(2.5f, result, 0.f);
    }
}
