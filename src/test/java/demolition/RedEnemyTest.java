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
public class RedEnemyTest {
    
    
    private RedEnemy redEnemy;
    private ArrayList<Level> levels;
    
    public RedEnemyTest() {
        
        redEnemy = new RedEnemy();
        levels = new ArrayList<>();
        
    }
    
    @BeforeEach
    public void beforeEach(){
        
        String data = Configurations.readConfig();
        
        levels = Configurations.configureLevels(data);
        
        
    }

    /**
     * Test of movement red enemy along y-axis
     */
    @Test
    public void testMovementAlongYAxis() {
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        
        redEnemy.redAnimDirection = 0;
        redEnemy.redEnDirection = 0;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));

        assertTrue(sampleMap[3][2] == 'R',"Red Enemy Movement Along Y-Axis");
        
        
    }
    
    
    
    /**
     * Test of movement red enemy along x-axis
     */
    @Test
    public void testMovementAlongXAxis() {
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'R',"Red Enemy Movement Along X-Axis");
        
        
    }
    
    
    /**
     * Test Red Enemy movement against player
     */
    
    @Test 
    public void testMovementAgainstPlayer(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'P';
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'R',"Red Enemy Movement Against Player");
        
        
        
    }
    
    
    /**
     * Test Red Enemy Movement Against Explosion
     */
    
    @Test
    public void testMovmentAgainstExplosion(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'H';
        
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'H',"Red Enemy Movement Against Explosion");
        
        
    }
    
    
    
    /**
     * Test Red Enemy Movement Against Yellow Enemy
     */
    
    @Test
    public void testMovmentAgainstYellowEnemy(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'Y';
        
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'X',"Red Enemy Movement Against Yellow Enemy");
        
        
    }
    
    
    /**
     * Test movement of red enemy against block (wall or broken wall)
     */
    @Test
    public void testMovementAgainstWall(){
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'W';
//        sampleMap[3][2] = 'W';
//        sampleMap[1][2] = 'W';
        
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
        
        assertTrue(sampleMap[2][1] == 'R' || sampleMap[1][2] == 'R' || sampleMap[3][2] == 'R',"Red Enemy Movement Against Wall");
        
        
        
    }
    
    
    
     /**
     * Test movement of red enemy against block and player 1(wall or broken wall)
     */
    @Test
    public void testRandomMovementAgainstPlayer1(){
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'W';
        sampleMap[3][2] = 'W';
        sampleMap[1][2] = 'W';
        sampleMap[2][1] = 'P';
        
        
        redEnemy.redAnimDirection = 2;
        redEnemy.redEnDirection = 2;
        
        sampleMap = redEnemy.movement(2, 2, 1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][1] == 'R',"Red Enemy Movement Against Wall and Player 1");
        
        
        
    }
    
    
     /**
     * Test movement of red enemy against block and player 2 (wall or broken wall)
     */
    @Test
    public void testRandomMovementAgainstPlayer2(){
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'P';
        sampleMap[3][2] = 'P';
        sampleMap[1][2] = 'P';
        sampleMap[2][1] = 'W';
        
        
        redEnemy.redAnimDirection = 3;
        redEnemy.redEnDirection = 3;
        
        sampleMap = redEnemy.movement(2, 2, -1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'R' || sampleMap[3][2] == 'R' || sampleMap[1][2] == 'R',"Red Enemy Movement Against Wall And Player 2");
        
        
        
    }
    
    
    /**
     * Test movement of Red Enemy against wall towards right
     */
    
    @Test
    public void testRandomMovementAlongXAxis2(){
        
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[1][2] = 'W';
        sampleMap[3][2] = 'W';
        sampleMap[2][1] = 'W';
        
        redEnemy.redAnimDirection = 3;
        redEnemy.redEnDirection = 3;
        
        sampleMap = redEnemy.movement(2, 2, -1, sampleMap);
        
        
//        System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[2][3] == 'R',"Red Enemy Movement Along X-Axis 2");
        
    }
    
    
    /*
     Test Red Enemy Movement Controller 
    */
    @Test
    public void testRedEnemyMovementController(){
        
        App.map = levels.get(0).mapArray;
        
        redEnemy.redFrameCounter = 60;
        redEnemy.redEnDirection = 3;
        
        
        redEnemy.controller(5, 8);
        
        redEnemy.controller(5, 8);
        
        redEnemy.redEnDirection = 1;
        redEnemy.redFrameCounter = 60;
        redEnemy.controller(5, 7);
        
        assertTrue(App.map[4][7] == 'R',"Test Red Enemy Movement Controller");
//        System.out.println(getMapStr(App.map));
        
        
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
