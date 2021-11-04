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
public class MapTest {
    
    private ArrayList<Level> levels;
    
    public MapTest() {
        
        
        
    }
    
    @BeforeEach
    public void beforeEach(){
        
        String data = Configurations.readConfig();
        levels = Configurations.configureLevels(data);
        
    }

    
    /*
    Test Map Details
    */
    @Test
    public void testMapDetails() {
        
        boolean areMapDetailsValid = false;
        
        Map map = new Map();
        
        if(Map.COLUMNS == 15 && Map.ROWS == 13 && Map.OFFSET == 64)
            areMapDetailsValid = true;
        
        assertTrue(areMapDetailsValid);
        
    }
    
    /*
    Test Map Validate method
    */
    @Test
    public void testMap1(){
        
        
        char[][] sampleMap = {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
                              };
        
        
        assertFalse(Map.validateMap(sampleMap));
         
        
    }
    
    
    /*
    Test Map Validate method
    */
     @Test
    public void testMap2(){
        
        
        char[][] sampleMap = {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W','W','W','W','W','W','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W','P',' ',' ',' ',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W',' ',' ',' ','G',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W','W','W','W','W','W','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
                              };
        
        
        assertTrue(Map.validateMap(sampleMap));
         
        
    }
    
    
    /*
    Test Map Validate method
    */
      @Test
    public void testMap3(){
        
        
        char[][] sampleMap = {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W','W','W','W','W','W','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W','P',' ',' ',' ',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W',' ',' ',' ',' ',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ','W',' ',' ',' ','G',' ','W',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
                              };
        
        
        assertFalse(Map.validateMap(sampleMap));
         
        
    }
    
    
    /*
    Test Map Validate method
    */
    @Test
    public void testValidateMap4(){
    
        char[][] sampleMap = levels.get(0).mapArray;
        
        sampleMap[0][5] = ' ';
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
    
     /*
    Test Map Validate method
    */
    @Test
    public void testValidateMap5(){
    
        char[][] sampleMap = createEmptyMap();
        
        
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
      /*
    Test Map Validate method
    */
    @Test
    public void testValidateMap6(){
    
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'P';
        
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap7(){
    
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'G';
        
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
     /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap8(){
    
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'P';
        sampleMap[2][5] = 'G';
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
     /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap9(){
    
        char[][] sampleMap = levels.get(0).mapArray;
        
        
        
        assertTrue(Map.validateMap(sampleMap));
        
        
    
    }
    
    
       /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap10(){
    
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[0][0] = 'W';
        sampleMap[1][0] = 'W';
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
    
       /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap11(){
    
        char[][] sampleMap = levels.get(0).mapArray;
        
        sampleMap[5][0] = ' ';
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
        /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap12(){
    
        char[][] sampleMap = levels.get(0).mapArray;
        
        sampleMap[1][1] = ' ';
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
    }
    
    
         /*
     Test Map Validate method
    */
    @Test
    public void testValidateMap13(){
    
        char[][] sampleMap = levels.get(0).mapArray;
        
        sampleMap[11][13] = ' ';
        
        assertFalse(Map.validateMap(sampleMap));
        
        
    
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
