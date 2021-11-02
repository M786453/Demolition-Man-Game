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
public class BombGuyTest {
    
    private BombGuy bombGuy;
    private ArrayList<Level> levels;
    
    public BombGuyTest() {
        bombGuy = new BombGuy();
        levels = new ArrayList<>();
        
    }
    
    
    @BeforeEach
    public void beforeEach(){
        
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
     * Test of movement method, of class BombGuy.
     */
    @Test
    public void testMovement1() {
        
      
      bombGuy.direction = 0;  
        
      char[][] sampleMap =  bombGuy.movement(1, 1, 1, levels.get(0).mapArray);
      
      
//       System.out.println(getMapStr(sampleMap));


       assertTrue((sampleMap[2][1] == 'P'),"Player Move Down through empty tile");
        
        
        
    }
    
    
    /*
    Test movement of Player against wall
    */
    
    @Test
    public void testMovement2() {
        
      
      bombGuy.direction = 2;  
        
        char[][] sampleMap = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', 'p', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', 'W', ' ', ' ', ' ', 'G', ' ', 'W', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
      
      
       sampleMap = bombGuy.movement(2, 1, 1, sampleMap);
      
//       System.out.println(getMapStr(sampleMap));


       assertTrue((sampleMap[2][2] == 'R'),"Player will die when in contact with enemy");
        
        
        
    }
    
    
    
    private String getMapStr(char[][] map){
        
        
        String mapStr = "";
        
        for(int i=0;i<Map.ROWS;i++){
            
            
            for(int j=0;j<Map.COLUMNS;j++){
                
                mapStr += map[i][j];
                
            }
            
            mapStr += "\n";
            
        }
        
        
        return mapStr;
        
    }
    
}
