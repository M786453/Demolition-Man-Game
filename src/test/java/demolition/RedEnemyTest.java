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
        
        redEnemy.redEnDirection = 0;
        redEnemy.redFrameCounter = 60;
        redEnemy.controller(4, 7);
        
        redEnemy.redEnDirection = 2;
        redEnemy.redFrameCounter = 60;
        redEnemy.controller(5, 7);
        
        
        redEnemy.redEnDirection = -1;
        redEnemy.redFrameCounter = 60;
        redEnemy.controller(5, 8);
        
        
        assertTrue(App.map[5][8] == 'R',"Test Red Enemy Movement Controller");
//        System.out.println(getMapStr(App.map));
        
        
    }
    
    /**
     * 
     * Test Red Enemy Movement method
     */
    
    @Test
    public void testRedEnemyMovement(){
        
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'R';
        
        redEnemy.redEnDirection = 1;
        redEnemy.movement(2, 2, -1, sampleMap);

//        System.out.println(getMapStr(sampleMap));
        redEnemy.movement(2, -1, 1, sampleMap);
 
        redEnemy.movement(-1, 2, 1, sampleMap);
        
        redEnemy.movement(18, 2, 1, sampleMap);
        
        redEnemy.movement(2, 18, 1, sampleMap);
        
        redEnemy.movement(18,18, 1, sampleMap);
        
        redEnemy.movement(-1,-1, 1, sampleMap);
        
        redEnemy.redEnDirection = -1;
        redEnemy.movement(1, 2, -1, sampleMap);
        

        
        assertTrue(sampleMap[1][2] == 'R',()-> "Test Red Enemy movement by giving valid and invalid positions");
        
    }
    
    
    /*
    Test Red Enemy movement Against movement blockers
    */
    
    @Test
    public void testRedEnemyMovementAgainstBlockers(){
        
        
        
        
    }
    
    
    /*
      Test Red enemy movement against explosion
    */
    
    @Test
    public void testRedEnemyMovementAgainstExplosion(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[3][2] = 'H';
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'R';
        sampleMap[3][2] = 'V';
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'R';
        sampleMap[3][2] = 'C';
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap[2][2] = 'R';
        sampleMap[3][2] = 'L';
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        assertTrue(sampleMap[2][2] == 'R',"Test red enemy movement against explosion");
//        System.out.println(getMapStr(sampleMap));
        
        
    }
    
    
    
        /*
    Test Red Enemy against soild, broken wall and bomb
    */
    
    @Test
    public void testRedMovementAgainstSolidAndBrokenWall(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'B';
        
        redEnemy.redEnDirection = 2;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        sampleMap = createEmptyMap();
        
        
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'W';
        
        redEnemy.redEnDirection = 2;
        
        
        redEnemy.movement(2, 2, 1, sampleMap);

        
//        System.out.println(getMapStr(sampleMap));


        
        
    }
    
    /*
    Test Red Enemy Movement Against Bomb
    */
    
    @Test
    public void testRedEnemyMovementAgainstBomb(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        Bomb.bombX = 2;
        Bomb.bombY = 2;
        
        redEnemy.redEnDirection = 2;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        redEnemy.movement(2, 1, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        

        
    }
    
    
    /*
    Test Red Enemy Movement against goal tile
    */
    @Test
    public void testYellowEnemyMovementAgainstGoalTile(){
        
        char[][] sampleMap = createEmptyMap();
        
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'G';
        
        App.GOAL_INDEX[0] = 2;
        App.GOAL_INDEX[1] = 3;
        
        redEnemy.redEnDirection = 2;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
        redEnemy.movement(2, 3, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));

        assertTrue(sampleMap[2][3] == 'G',()-> "Test Red Enemy Movement against goal tile");
        
        
    }
    
    
    /*
    Test Red Enemy Random Movement Against bomb 1 Up
    */
    
    @Test 
    public void testRedEnemyRandomMovementAgainstBomb1UP(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        Bomb.bombX = 1;
        Bomb.bombY = 1;
        
        redEnemy.redEnDirection = 1;
        
        redEnemy.movement(2, 1, -1, sampleMap);
       
        
        
        
        
    }
    
    /*
    Test Red Enemy Random Movement Against bomb 2 Up
    */
    @Test
    public void testRedEnemyMovementAgainstBomb2UP(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        redEnemy.redEnDirection = 1;
        Bomb.bombX = 3;
        Bomb.bombY = 1;
        redEnemy.movement(2, 1, -1, sampleMap);
       
        
        
    }
    
    
     /*
    Test Red Enemy Random Movement Against bomb 3 Up
    */
    @Test
    public void testRedEnemyMovementAgainstBomb3UP(){
        
         char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        sampleMap[1][1] = ' ';
        Bomb.bombX = -1;
        Bomb.bombY = -1;
        redEnemy.redEnDirection = 1;
        
        redEnemy.movement(2, 1, -1, sampleMap);
        
        
        
    }
    
    
     /*
    Test Red Enemy Random Movement Against bomb 4 Up
    */
    @Test
    public void testRedEnemyMovementAgainstBomb4UP(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        sampleMap[1][1] = 'Y';
        Bomb.bombX = -1;
        Bomb.bombY = -1;
        redEnemy.redEnDirection = 1;
        
        redEnemy.movement(2, 1, -1, sampleMap);
        
        
        
    }
    
    
       /*
    Test Red Enemy Random Movement Against bomb 5 Up
    */
    @Test
    public void testRedEnemyMovementAgainstBomb5UP(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][1] = 'R';
        sampleMap[1][1] = 'L';
        Bomb.bombX = -1;
        Bomb.bombY = -1;
        redEnemy.redEnDirection = 1;
        
        redEnemy.movement(2, 1, -1, sampleMap);
        
        
        
    }
    
    
       /*
    Test Red Enemy Random Movement Against bomb 1 Up
    */
//    
    @Test 
    public void testRedEnemyRandomMovementAgainstBomb1DOWN(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][3] = 'R';
        Bomb.bombX = 1;
        Bomb.bombY = 3;
        
        redEnemy.redEnDirection = 0;
        
        redEnemy.movement(2, 1, 1, sampleMap);
       
        
        
        
        
    }
    
    /*
    Test Red Enemy Random Movement Against bomb 2 Down
    */
    @Test
    public void testRedEnemyMovementAgainstBomb2DOWN(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[4][4] = 'R';
        Bomb.bombX = 1;
        Bomb.bombY = 3;
        
        redEnemy.redEnDirection = 0;
        
        redEnemy.movement(4, 4, 1, sampleMap);
       
        
        
    }
    
    
    /*
        Test Red Enemy Random Movement No path found
    */
    @Test
    public void testRedEnemyRandomMovementNoPathFound(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'Y';
        sampleMap[2][1] = 'Y';
        sampleMap[1][2] = 'Y';
        sampleMap[3][2] = 'W';
        
        redEnemy.redEnDirection = 0;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        
    }
    
    /*
        Test Red Enemy Random Movement No path found 2
    */
    @Test
    public void testRedEnemyRandomMovementNoPathFound2(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'L';
        sampleMap[2][1] = 'L';
        sampleMap[1][2] = 'L';
        sampleMap[3][2] = 'W';
        
        redEnemy.redEnDirection = 0;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        
    }
    
    /*
        Test Red Enemy Random Movement No path found 2
    */
    
    @Test
    public void testRedEnemyRandomMovementNoPathFound3(){
        
        char[][] sampleMap = createEmptyMap();
        sampleMap[2][2] = 'R';
        sampleMap[2][3] = 'W';
        sampleMap[2][1] = 'G';
        sampleMap[1][2] = 'W';
        sampleMap[3][2] = 'W';
        
        App.GOAL_INDEX[0] = 2;
        App.GOAL_INDEX[1] = 1;
        
        redEnemy.redEnDirection = 0;
        
        redEnemy.movement(2, 2, 1, sampleMap);
        
//        System.out.println(getMapStr(sampleMap));
        
        
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
