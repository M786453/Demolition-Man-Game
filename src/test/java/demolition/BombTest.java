/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package demolition;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PApplet;

/**
 *
 * @author Ahtesham Sarwar
 */
public class BombTest extends PApplet {
    
    
    static ArrayList<Level> levels;
    
    
    public BombTest() {
        
       
      
        
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
     * Test of explode method, of class Bomb.
     */
    @Test
    public void testExplodeWithWall() {
        
        
        Bomb bomb = new Bomb();
        
        char[][] mapAfterExplosion = bomb.explode(1, 1, levels.get(0).mapArray);
       
        boolean isExplodedRight = false;
        
        if(mapAfterExplosion[1][2] == 'H' && mapAfterExplosion[1][3] == 'H' && mapAfterExplosion[2][1] == 'V' 
                && mapAfterExplosion[3][1] == 'V' && mapAfterExplosion[1][1] == 'C'){
           
            isExplodedRight = true;
            
        }
        
        
        assertTrue(isExplodedRight,"Bomb Explosion With Wall");
       
    }
    
    
    @Test
    public void testExplodeWithBrokenWall(){
        
        Bomb bomb = new Bomb();

        char[][] mapAfterExplosion = bomb.explode(7, 7, levels.get(0).mapArray);

        boolean isExplodedRight = false;

        if ( mapAfterExplosion[7][5] == 'H' && mapAfterExplosion[7][6] == 'H' 
                && mapAfterExplosion[7][8] == 'H' && mapAfterExplosion[7][9] == 'H' && 
                mapAfterExplosion[6][7] == 'V'  && mapAfterExplosion[5][7] == 'V' && 
                mapAfterExplosion[8][7] == 'V'  && mapAfterExplosion[9][7] == 'V' && 
                mapAfterExplosion[7][7] == 'C') {

            isExplodedRight = true;

        }
        
        
        
//        System.out.println(getMapStr(mapAfterExplosion));
        

        assertTrue(isExplodedRight,"Bomb Explosion With Broken Wall");
        
    }
    
    
    
    @Test
    public void testExplodeWithEmptyTilesInSurrounding(){
        
        
        Bomb bomb = new Bomb();

        char[][] mapAfterExplosion = bomb.explode(3, 3, levels.get(0).mapArray);

        boolean isExplodedRight = false;

        if ( mapAfterExplosion[3][2] == 'H' && mapAfterExplosion[3][1] == 'H' 
                && mapAfterExplosion[3][4] == 'H' && mapAfterExplosion[3][5] == 'H' && 
                mapAfterExplosion[1][3] == 'V'  && mapAfterExplosion[2][3] == 'V' && 
                mapAfterExplosion[4][3] == 'V'  && mapAfterExplosion[5][3] == 'V' && 
                mapAfterExplosion[3][3] == 'C') {

            isExplodedRight = true;

        }

//        System.out.println(getMapStr(mapAfterExplosion));
        
        assertTrue(isExplodedRight, "Bomb Explosion With Empty Surrounding");
        
        
    }
    
    
    @Test
    public void explodeTest1(){
        
        Bomb bomb = new Bomb();

        char[][] mapAfterExplosion = bomb.explode(0, 0, levels.get(0).mapArray);

        boolean isExplodedRight = false;

        if (mapAfterExplosion[0][0] == 'C') {

            isExplodedRight = true;

        }

//        System.out.println(getMapStr(mapAfterExplosion));

        assertTrue(isExplodedRight, "Bomb Explosion Test1");
        
        
    }
    
    
    
    @Test
    public void explodeTest2(){
        
        
        Bomb bomb = new Bomb();
        

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
        
        
        char[][] mapAfterExplosion = bomb.explode(1, 1, sampleMap);

        boolean isExplodedRight = false;

        if ( mapAfterExplosion[1][2] == 'H' && mapAfterExplosion[1][3] == 'H' 
                && 
                mapAfterExplosion[2][1] == 'V'  && mapAfterExplosion[3][1] == 'V' && 
                mapAfterExplosion[1][1] == 'C') {

            isExplodedRight = true;

        }

//        System.out.println(getMapStr(mapAfterExplosion));

        assertTrue(isExplodedRight, "Bomb Explosion Test2");
        
        
        
    }
    
    
    
    @Test
    public void explodeTest3(){
        
        
        Bomb bomb = new Bomb();
        

        char[][] sampleMap = {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ','B',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ','B',' ','B',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                              {' ',' ','B',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
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
        
        
        char[][] mapAfterExplosion = bomb.explode(2, 2, sampleMap);

        boolean isExplodedRight = false;

        if ( mapAfterExplosion[2][1] == 'H' 
                && mapAfterExplosion[2][3] == 'H' && 
                mapAfterExplosion[1][2] == 'V' && 
                mapAfterExplosion[3][2] == 'V'   && 
                mapAfterExplosion[2][2] == 'C') {

            isExplodedRight = true;

        }

//        System.out.println(getMapStr(mapAfterExplosion));

        assertTrue(isExplodedRight, "Bomb Explosion Test3");
        
        
        
    }
    
    
    /*
     Test placeBomb function of Bomb
    */
    @Test
    public void testBombPlacing() throws InterruptedException{
        
        Bomb bomb = new Bomb();
        
        char[][] sampleMap = levels.get(0).mapArray;
        App.map = sampleMap;
        App.bombAnimation = new Animation(this,bomb.PER_FRAME_TIME);
        App.GOAL_INDEX[0] = 11;
        App.GOAL_INDEX[1] =13;
        bomb.placeBomb(1, 1);
        
        sleep(2500);
        
        bomb.removeExplosionEffectAfterExplosion();
        
        sleep(1000);
        

        
//        System.out.println(getMapStr(sampleMap));
        
        assertTrue(Bomb.isExploded);
        
    }
    
    /*
        Test remove explosion effect method when canExplode is false
    */
    @Test
    public void testRemoveExplosionRange() throws InterruptedException{
        
        
       
        
       char[][] sampleMap = createEmptyMap();
//       sampleMap[2][2] = 'B';
       sampleMap[1][2] = 'W';
       sampleMap[3][2] = 'W';
       sampleMap[2][3] = 'W';
       sampleMap[2][1] = 'W';
       
       App.map = sampleMap;
       
       Bomb bomb = new Bomb();
       
//       bomb.placeBomb(2, 2);
       
       Bomb.bombX = 2;
       
       Bomb.bombY = 2;

       bomb.canExplode = true;
       
       bomb.explode(2, 2, sampleMap);
       
       
       
       bomb.removeExplosionEffectAfterExplosion();
       
       sleep(1000);
        
       assertTrue(sampleMap[2][2] == ' ',"Test Explosion Range 1");
//       System.out.println(getMapStr(sampleMap));
       
    }
    
    
     /*
        Test remove explosion effect method when canExplode is false
    */
    @Test
    public void testRemoveExplosionRange2(){
        
        
        Bomb bomb = new Bomb();
        
        char[][] sampleMap = createEmptyMap();
        
        bomb.canExplode = false;
        
//        bomb.explode(2,2,sampleMap);
        
        
        bomb.removeExplosionEffectAfterExplosion();
        
        
        assertTrue(sampleMap[2][2] == ' ');
        
        
        
    }
    
    /*
    Test Bomb Explosion Range method with +ve invalid values
    */
    
    @Test
    public void testBombExplosionRange1(){
        
        char[][] sampleMap = createEmptyMap();
        
        Bomb bomb = new Bomb();
        
        bomb.explosionRange(15, 15, 15, 15, sampleMap, 'V');
        
        boolean isMapEmpty = true;
        
        for(int i=0;i<Map.ROWS;i++){
         
            for(int j=0;j<Map.COLUMNS;j++){
             
                if(sampleMap[i][j] != ' ')
                    isMapEmpty = false;
                
            }
            
        }
        
        
        assertTrue(isMapEmpty,"Test Bomb Explosion Range method with +ve invalid values");
        
        
    }
   
    
    
    /*
    Test Bomb Explosion Range method
    */
    
    @Test
    public void testBombExplosionRange2() throws InterruptedException{
        
       char[][] sampleMap = levels.get(0).mapArray;
       
       Bomb bomb = new Bomb();
       Bomb.bombX = 12;
       Bomb.bombY = 11;
       
       bomb.canExplode = true;
       
       App.GOAL_INDEX[0] = 11;
       App.GOAL_INDEX[1] = 12;
       
//       bomb.explode(12, 11, sampleMap);

        bomb.removeExplosionEffectAfterExplosion();
       
       sleep(2500);
       
//       System.out.println(sampleMap[11][13]);
        assertTrue(sampleMap[11][12] == ' ');
        
        
        
    }
    
    
    
    
    /*
    Test Bomb Explosion Range method
    */
    
    @Test
    public void testBombExplosionRange3() throws InterruptedException{
        
       char[][] sampleMap = levels.get(0).mapArray;
       
       Bomb bomb = new Bomb();
       Bomb.bombX = 12;
       Bomb.bombY = 11;
       
       bomb.canExplode = true;
       
       App.GOAL_INDEX[0] = 11;
       App.GOAL_INDEX[1] = 13;
       
//       bomb.explode(12, 11, sampleMap);

        bomb.removeExplosionEffectAfterExplosion();
       
       sleep(2500);
       
//       System.out.println(getMapStr(sampleMap));
        assertTrue(sampleMap[11][13] == 'G');
        
        
        
    }
    
    
    
        /*
    Test Bomb Explosion Range method
    */
    
    @Test
    public void testBombExplosionRange4() throws InterruptedException{
        
       char[][] sampleMap = levels.get(0).mapArray;
       
       Bomb bomb = new Bomb();
       Bomb.bombX = 11;
       Bomb.bombY = 11;
       
       bomb.canExplode = true;
       
       App.GOAL_INDEX[0] = 11;
       App.GOAL_INDEX[1] = 13;
       
//       bomb.explode(12, 11, sampleMap);

        bomb.removeExplosionEffectAfterExplosion();
       
       sleep(2500);
       
//       System.out.println(getMapStr(sampleMap));
        
        assertTrue(sampleMap[11][13] == 'G');
        
        
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
    
    
    private String getMapStr(char[][] mapAfterExplosion){
        
        
        String mapStr = "";
        
        for(int i=0;i<Map.ROWS;i++){
            
            
            for(int j=0;j<Map.COLUMNS;j++){
                
                mapStr += mapAfterExplosion[i][j];
                
            }
            
            mapStr += "\n";
            
        }
        
        
        return mapStr;
        
    }
    
    
}
