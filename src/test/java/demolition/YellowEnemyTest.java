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
public class YellowEnemyTest {
    
    private ArrayList<Level> levels = new ArrayList<>();
    private YellowEnemy yellowEnemy;
    
    public YellowEnemyTest() {
        
        yellowEnemy = new YellowEnemy();
       
    }
    
    
    @BeforeEach
    public void beforeEach(){
        
        String jsonData = Configurations.readConfig();
        
        levels = Configurations.configureLevels(jsonData);
        
    }
    

    /**
     * Test of movement method, of class YellowEnemy.
     * Test Down Movement of Yellow Enemy using movement method
     */
    @Test
    public void testMovementDown() {
        
        char[][] sampleMap = levels.get(0).mapArray;
        
        
        yellowEnemy.yellowEnDirection = 0;
        yellowEnemy.yellowAnimDirection = 0;
        
        sampleMap = yellowEnemy.movement(9,5, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[10][5] == 'Y',"Yellow Enemy Down Movement");
        
        
    }
    
    /**
     * Test Left movement of Yellow Enemy using movement method
     */
    
    @Test
    public void testMovementLeft() {
        
        char[][] sampleMap = levels.get(0).mapArray;
        
        
        yellowEnemy.yellowEnDirection = 3;
        yellowEnemy.yellowAnimDirection = 3;
        
        sampleMap = yellowEnemy.movement(9,5, -1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[9][4] == 'Y',"Yellow Enemy Left Movement");
        
        
    }
    
    /**
     * Test Clockwise movement of yellow enemy when collide with wall or broken wall
     * 
     */
    
    @Test
    public void testClockwiseMovement() {
        
        char[][] sampleMap = levels.get(0).mapArray;
        
        
        yellowEnemy.yellowEnDirection = 0;
        yellowEnemy.yellowAnimDirection = 0;
        
        sampleMap = yellowEnemy.movement(9,5, 1, sampleMap);
        sampleMap = yellowEnemy.movement(10,5, 1, sampleMap);
        sampleMap = yellowEnemy.movement(11,5, 1, sampleMap);
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[11][4] == 'Y',"Yellow Enemy Clockwise Movement");
        
        
        
        
    }
    
    
    
    /**
     * Test Yellow enemy movement against red enemy
     */
    
    @Test
    public void testMovementAgainstRed(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'R';
        
        yellowEnemy.yellowEnDirection = 2;
        yellowEnemy.yellowAnimDirection = 2;
        
        sampleMap = yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[2][3] == 'X',"Yellow Enemy movement through Red Enemy");
        
        
    }
    
    
    /**
     * Test Yellow Enemy Movement Against Player
     */
    
    @Test
    public void testMovementAgainstPlayer(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'P';
        
        yellowEnemy.yellowEnDirection = 2;
        yellowEnemy.yellowAnimDirection = 2;
        
        sampleMap = yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[2][3] == 'Y',"Yellow Enemy movement through Red Enemy");
        
        
    }
    
    /**
     * Test Yellow Enemy movement Against Explosion
     */
    
    
     @Test
    public void testMovementAgainstExplosion(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'H';
        
        yellowEnemy.yellowEnDirection = 2;
        yellowEnemy.yellowAnimDirection = 2;
        
        sampleMap = yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[2][3] == 'H',"Yellow Enemy movement through Red Enemy");
        
        
    }
    
    
    /**
     * Test Clockwise movement against Red
     */
    
    @Test 
    
    public void testClockwiseMovementAgainstRed(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[3][3] = 'Y';
        sampleMap[3][2] = 'W';
        sampleMap[2][3] = 'R';
        
        yellowEnemy.yellowEnDirection = 3;
        yellowEnemy.yellowAnimDirection = 3;
        
        sampleMap = yellowEnemy.movement(3, 3, -1, sampleMap);
        
//        System.out.print(getMapStr(sampleMap));
        
        
        assertTrue(sampleMap[2][3] == 'X',"Yellow Enemy Clockwise Movement Against Red");
        
        
        
        
    }
    
    
    /**
     * Test clockwise movement of enemy against player
     */
    
    
    @Test
    public void testClockwiseMovementAgainstPlayer(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[3][3] = 'Y';
        sampleMap[2][3] = 'W';
        sampleMap[3][4] = 'P';
        
        yellowEnemy.yellowEnDirection = 1;
        yellowEnemy.yellowAnimDirection = 1;
        
        sampleMap = yellowEnemy.movement(3, 3, -1, sampleMap);
        
//        System.out.print(getMapStr(sampleMap));
        assertTrue(sampleMap[3][4] == 'Y',"Yellow Enemy Clockwise Movement Against Player");
        
        
    }
    
    
    
    /**
     * Test clockwise movement of yellow enemy against explosion
     */
    
    @Test
    public void testClockwiseMovementAgainstExplosion(){
        
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'W';
        sampleMap[3][2] = 'H';
        
        yellowEnemy.yellowEnDirection = 2;
        yellowEnemy.yellowAnimDirection = 2;
        
        sampleMap = yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.print(getMapStr(sampleMap));
        assertTrue(sampleMap[3][2] == 'H',"Yellow Enemy Clockwise Movement Against Player"); 
        
        
        
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
