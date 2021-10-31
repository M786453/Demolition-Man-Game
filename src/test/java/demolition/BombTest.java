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
public class BombTest {
    
    public BombTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of explode method, of class Bomb.
     */
    @Test
    public void testExplode() {
        
        
        Bomb bomb = new Bomb();
        
        char[][] sampleMap = {{'W','W','W'},
                              {'W',' ','W'},
                              {'W','W','W'}};
        
        
        char[][] mapAfterExplosion = bomb.explode(1, 1, sampleMap);
        
        int wallCounter = 0;
        
        for(int i=0;i<3;i++){
            
            for(int j=0;j<3;j++){
                
                if(mapAfterExplosion[i][j] == 'W')
                    wallCounter++;
                
                
            }
            
            
        }
        
        
        assertTrue(wallCounter == 8);
        
        
        
    }
    
}
