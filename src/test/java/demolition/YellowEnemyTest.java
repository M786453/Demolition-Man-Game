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
    
    
    
    
       /*
     Test Yellow Enemy Movement Controller 
    */
    @Test
    public void testYellowEnemyMovementController(){
        
        App.map = levels.get(0).mapArray;
        
        yellowEnemy.yellowFrameCounter = 60;
        yellowEnemy.yellowEnDirection = 3;
        
        
        yellowEnemy.controller(9, 5);
        
        yellowEnemy.controller(9, 5);
        
        yellowEnemy.yellowEnDirection = 1;
        yellowEnemy.yellowFrameCounter = 60;
        yellowEnemy.controller(9, 5);
        
        assertTrue(App.map[9][4] == 'Y',"Test Yellow Enemy Movement Controller");
//        System.out.println(getMapStr(App.map));
        
        
    }
    
    
    /**
     * Test Yellow Enemy movement by default
     */
    
    @Test
    public void testYellowEnemyMovment(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        yellowEnemy.yellowEnDirection = -1;
        
        sampleMap = yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.print(getMapStr(sampleMap));
        assertTrue(sampleMap[2][2] == 'Y',"Test Yellow Enemy movement by default"); 
        
    }
    
    
    /*
    Test yellow movement by giving different positions
    */
    
    @Test
    public void testYellowEnemyMovement1(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        
        yellowEnemy.yellowEnDirection = 1;
        yellowEnemy.movement(2, 2, -1, sampleMap);

//        System.out.println(getMapStr(sampleMap));
        yellowEnemy.movement(2, -1, 1, sampleMap);
 
        yellowEnemy.movement(-1, 2, 1, sampleMap);
        
        yellowEnemy.movement(18, 2, 1, sampleMap);
        
        yellowEnemy.movement(2, 18, 1, sampleMap);
        
        yellowEnemy.movement(18,18, 1, sampleMap);
        
        yellowEnemy.movement(-1,-1, 1, sampleMap);
        
//        yellowEnemy.yellowEnDirection = -1;
//        yellowEnemy.movement(-1, -1, 1, sampleMap);
        
        assertTrue(sampleMap[1][2] == 'Y',()-> "Test Yellow Enemy movement by giving valid and invalid positions");
        
    }
    
    
    /*
      Test yellow enemy movement against explosion
    */
    
    @Test
    public void testYellowEnemyMovementAgainstExplosion(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'H';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'V';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'C';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'L';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        assertTrue(sampleMap[2][2] == 'Y',"Test yellow enemy movement against explosion");
//        System.out.println(getMapStr(sampleMap));
        
        
    }
    
    /*
    Test Yellow Enemy Movement against goal tile
    */
    @Test
    public void testYellowEnemyMovementAgainstGoalTile(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'G';
        
        App.GOAL_INDEX[0] = 2;
        App.GOAL_INDEX[1] = 3;
        
        yellowEnemy.yellowEnDirection = 2;
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        yellowEnemy.movement(2, 3, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));

        assertTrue(sampleMap[2][3] == 'G',()-> "Test Yellow Enemy Movement against goal tile");
        
        
    }
    
    
    /*
    Test Yellow Enemy against soild, broken wall and bomb
    */
    
    @Test
    public void testYellowMovementAgainstSolidAndBrokenWall(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'Y';
        sampleMap[2][3] = 'B';
        
        yellowEnemy.yellowEnDirection = 2;
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        Bomb.bombX = 4;
        Bomb.bombY = 2;
        
        yellowEnemy.movement(3, 2, 1, sampleMap);
        
        
        
//        yellowEnemy.movement(3, 1, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));

//        assertTrue(sampleMap[3][2] == 'Y',"Test Yellow Enemy against soild, broken wall and bomb");
        
        
    }
    
    
    
    /*
      Test Yellow Enemy against all blocked paths
    */
    @Test
    public void testYellowEnemyMovementWithAllBlockedPaths(){
        
        char[][] sampleMap = createEmptyMap();
        
        yellowEnemy.yellowEnDirection = 2;
        
//        sampleMap[1][2] = 'W';
        sampleMap[3][2] = 'H';
        sampleMap[2][2] = 'Y';
//        sampleMap[2][1] = 'W';
        sampleMap[2][3] = 'W';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'V';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'C';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'Y';
        sampleMap[3][2] = 'L';
        
        yellowEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[2][1] == 'Y',"Test Yellow Enemy against all blocked paths");
        
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
