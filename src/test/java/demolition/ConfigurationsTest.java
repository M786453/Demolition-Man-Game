/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import java.util.ArrayList;
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
    private ArrayList<Level> levels;
    
    public ConfigurationsTest() {
    }
    
    
    @BeforeEach
    public void beforeEach(){
        
        configs = new Configurations();
        String data = Configurations.readConfig();
        
        levels = Configurations.configureLevels(data);
        
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
        
        
        String jsonData = null;
        
        Configurations.configureLevels(jsonData);
        
        jsonData = "";
        
        Configurations.configureLevels(jsonData);
        
        

        
    }
    
    
    /**
     * Test Read Map from file
     * @return 
     */
    
    @Test
    public void testReadMap(){
        
        String path = null;
        
        Configurations.readMapFromFile(path);
        
        path = "";
        
        Configurations.readMapFromFile(path);
        
        
    }
    
    
     private char[][] createEmptyMap(){
         
         char[][] emptyMap = new char[Map.ROWS][Map.COLUMNS];
         
         for(int i=0;i<Map.ROWS;i++){
             
             
             for(int j=0;j<Map.COLUMNS;j++){
              
                 emptyMap[i][j] = ' ';
                 
                 
             }
             
         }
         
         return emptyMap;                  
     }
    
}
