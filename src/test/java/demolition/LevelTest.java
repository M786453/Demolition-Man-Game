/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ahtesham Sarwar
 */
public class LevelTest {
    
    
    
    public LevelTest() {
        
        
        
        
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of setMap method, of class Level.
     */
    @Test
    public void testSetMap() {
        
        Level level = new Level("level1.txt",180);
        
        char[][] testMap = new char[13][15];
        
        for(int i=0;i<13;i++){
            
            for(int j=0;j<15;j++){
                
                
                testMap[i][j] = 'T';
                
            }
            
            
        }
        
        level.setMap(testMap);
        
        boolean isMapSetted = false;
        
        if(testMap.length == level.originalMap.length && testMap.length == level.mapArray.length)
            isMapSetted = true;
        
        
        assertTrue(isMapSetted);
        
        
        
        
    }

    /**
     * Test of resetLevel method, of class Level.
     */
    @Test
    public void testResetLevel() {
        
        Level level = new Level("level1.txt",180);
        
        char[][] testMap = new char[13][15];
        
        for(int i=0;i<13;i++){
            
            for(int j=0;j<15;j++){
                
                
                testMap[i][j] = 'T';
                
            }
            
            
        }
        
        level.setMap(testMap);
        
        
        level.mapArray[5][5] = 'C';
        
        level.resetLevel();
        
        boolean isLevelReseted = true;
        
        for(int i=0;i<13;i++){
            
         for(int j=0;j<15;j++){   
             
             if(level.mapArray[i][j] != level.originalMap[i][j])
                 isLevelReseted = false;
             
             
         }
            
        }
        
        
        assertTrue(isLevelReseted);
        
        
        
    }
    
}
