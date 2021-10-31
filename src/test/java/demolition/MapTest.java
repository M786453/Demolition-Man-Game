/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ahtesham Sarwar
 */
public class MapTest {
    
    public MapTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    @Test
    public void testMapDetails() {
        
        boolean areMapDetailsValid = false;
        
        Map map = new Map();
        
        if(Map.COLUMNS == 15 && Map.ROWS == 13 && Map.OFFSET == 64)
            areMapDetailsValid = true;
        
        assertTrue(areMapDetailsValid);
        
    }
    
}
