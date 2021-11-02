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
public class BombTest {
    
    
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
