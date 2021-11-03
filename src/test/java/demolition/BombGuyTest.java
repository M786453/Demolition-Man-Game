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
    Test movement of Player against red enemy
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
        
        
        App.map = sampleMap;
      
        
        
//       sampleMap = bombGuy.movement(2, 1, 1, sampleMap);
      
       bombGuy.controlPlayer(2, 1);
//       System.out.println(getMapStr(sampleMap));


       assertTrue((App.map[2][2] == 'R'),"Player will die when in contact with enemy");
        
        
        
    }
    
    
    /*
      Test Bomb Guy if caught in explosion , die and level will rest 
    */
    
    
    @Test
    public void controlMovementTest(){
        
        bombGuy.direction = 0;
        
        App.gameLevels = levels;
        
        App.map = levels.get(0).mapArray;
        
        bombGuy.controlPlayer(1, 1);
        Bomb.isExploded = false;
        
        bombGuy.playerDieAndResetLevel();
        
        Bomb.isExploded = true;
        
        bombGuy.playerDieAndResetLevel();
        
        App.map = levels.get(0).mapArray;
        
        
        assertTrue(3 != BombGuy.lives,"Player die test");
        
        boolean isMapReseted = true;
        
        for(int i=0;i<Map.ROWS;i++){
            
            for(int j=0;j<Map.COLUMNS;j++){
                
                if(App.map[i][j] != levels.get(0).originalMap[i][j])
                    isMapReseted = false;
                
            }
            
        }
        
        assertTrue(isMapReseted,"Reset Level test when player die");
        
        
        
        
    }
    
    /**
     * Test whether level changes or not when player reaches goal tile
     */
    
    @Test
    public void controlMovementTestToReachGoalTile(){
        
        bombGuy.direction = 2;
        
        App.gameLevels = levels;
        
        App.map = levels.get(0).mapArray;
        
        App.GOAL_INDEX[0] = 11;
        App.GOAL_INDEX[1] = 13;
        
        App.map[1][1] = ' ';
        App.map[11][12] = 'P';
        
        bombGuy.controlPlayer(12, 11);
        
        bombGuy.changeLevelIfPlayerAtGoal(11, 13);
        
        App.map = levels.get(1).mapArray;
        
        App.GOAL_INDEX[0] = 6;
        App.GOAL_INDEX[1] = 13;

//        System.out.println(App.gameLevels.size()-1);
//        System.out.println(App.levelIndex);
        
        bombGuy.changeLevelIfPlayerAtGoal(6, 13);
        
        bombGuy.changeLevelIfPlayerAtGoal(6, 2);
        
        bombGuy.changeLevelIfPlayerAtGoal(2, 2);
        
        assertTrue(App.isWin,()->"Test If player reaches goal tile, level will change");
        
    }
    
    /**
     * Test bombGuy if not any direction given by user
     */
    @Test
    public void controlMovementTest2(){
        
        
        
        App.gameLevels = levels;
        
        App.map = levels.get(0).mapArray;
        
        bombGuy.controlPlayer(1, 1);
        
        assertTrue(App.map[1][1] == 'P',"Test bombGuy if not any direction given by user");
        
//        System.out.println(getMapStr(App.map));
        
        
        
        
    }
    
    /**
     * Test Bomb Guy movement towards left
     */
    @Test
    public void controlMovementTest3(){
        
        bombGuy.direction = 3;
        
        App.gameLevels = levels;
        
        App.map = levels.get(0).mapArray;
        
        bombGuy.controlPlayer(1, 1);
        
        assertTrue(App.map[1][1] == 'P',"Test Bomb Guy movement towards left");
//        System.out.println(getMapStr(App.map));
        
        
        
        
    }
    
    
    
    /**
     * Test of Bomb Guy movement towards up
     */
    @Test
    public void controlMovementTest4(){
        
        bombGuy.direction = 1;
        
        App.gameLevels = levels;
        
        App.map = levels.get(0).mapArray;
        
        bombGuy.controlPlayer(1, 1);
        
        assertTrue(App.map[1][1] == 'P',"Test of Bomb Guy movement towards up");
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
    
}
