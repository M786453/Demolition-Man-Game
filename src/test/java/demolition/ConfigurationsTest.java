/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Ahtesham Sarwar
 */
public class ConfigurationsTest {
    
    
    private Configurations configs;
    
    
    public ConfigurationsTest() {
    }
    
    
    @BeforeEach
    public void beforeEach(){
        
        configs = new Configurations();
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of readConfig method, of class Configurations.
     */
    @Test
    public void testReadConfig() {
        
        
        
    }

    /**
     * Test of configureLevels method, of class Configurations.
     */
    @Test
    public void testConfigureLevels() {
        
        
//        Exception assertThrows = assertThrows(Exception.class,() -> Configurations.configureLevels(""));
//        assertEquals("A JSONObject text must begin with '{'",assertThrows.getMessage());
        
        
    }
    
}
