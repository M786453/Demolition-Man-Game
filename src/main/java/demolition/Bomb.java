/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represent the Bomb in Game Which will explode within 2 seconds after placing
 * @author Ahtesham Sarwar
 */
public class Bomb {
    
    /**
     * Represent the time(in milliseconds) after which bomb will explode
     */
    public final int BOMB_TIME = 2000; 
    
    /**
     * Represents the time of one frame in Animation Cycle
     */
    public final float PER_FRAME_TIME = 0.25f;
    
    /**
     * Represents the time(in milliseconds) of explosion effect 
     */
    public final int EXPLOSION_TIME = 500; // it is in milliseconds
    
    /**
     * Represents the X-axis Position of Bomb
     */
    public static int bombX = -1;
    
    /**
     * Represents the Y-axis Position of Bomb
     */
    public static int bombY = -1;
    
    /**
     * Represents whether Bomb is placed or not
     */
    public boolean isPlaced;
    
    /**
     * Represents the time when Bomb is placed
     */
    public long placeTime;
    
    /**
     * Represents whether the Bomb is exploded or not
     */
    
    public static boolean isExploded;
    
    /**
     * Represents whether Bomb can explode or not
     */
    public boolean canExplode;   
    
    
    /**
     * Represents the range of explosion in each of four directions (down,right,up,left)
     */
    public ArrayList<int[][]> explodeRange;
    
    /**
     * Constructor of Bomb Class
     */
    
    public Bomb(){                     
        
        
        
        isPlaced = false;
        isExploded = true;
        placeTime = 0;
        canExplode = false;
        explodeRange = new ArrayList<>();
        
    }
    
    
    
    /**
     * Used to explode the bomb at given positions in map
     * @param x Represents the X-axis position of Bomb
     * @param y Represents the Y-axis position of Bomb
     * @param map Represents the Map current level
     * @return  Return modified map (2-D Char Array) 
     */
    public char[][] explode(int x,int y,char[][] map){
        
        explodeRange.clear();
        
        map[y][x] = 'C'; // y -> y-axis , x -> x-axis
        
        for(int i=0;i<4;i++){
            
            
                
                switch(i){
                    
                    case 0:
                        //down
                        
                        
                        explosionRange(x,x,y+1,y+2,map,'V');
                        
                        
                        
                        
                        break;
                    case 1:
                        //up
                        
                        
                        explosionRange(x,x,y-1,y-2,map,'V');
                        
                        
                        break;
                    case 2:
                        //right
                        
                        
                        
                        explosionRange(x+1,x+2,y,y,map,'H');
                        
                                            
                        
                        break;
                    case 3:
                        //left
                        
                        
                        // pattern eg: L,H
                        explosionRange(x-1,x-2,y,y,map,'H');
                        

                        break;
                    
                }
                
        }
        
        return map;
    }
    
    
    
    /**
     * Used to find the explosion range of bomb on given positions and modify the map accordingly
     * @param x1 Represents the X-axis Position of First Grid (after bomb grid) in map
     * @param x2 Represents the X-axis Position of Second Grid (after bomb grid) in map
     * @param y1 Represents the Y-axis Position of First Grid (after bomb grid) in map
     * @param y2 Represents the Y-axis Position of Second Grid (after bomb grid) in map
     * @param map Represents the Map of current level
     * @param explosionChar Represents the char used for explosion
     */
    private void explosionRange(int x1,int x2,int y1,int y2,char[][] map,char explosionChar){
        
                int[][] rangeForDirection = new int[2][2];
        
                if(y1 >= 0 && y1 < 12 && x1 >= 0 && x1 < 14){
                            
                    
                    
                            if(y2 > 0 && y2 < 12 && x2 > 0 && x2 < 14){
                                
                                rangeForDirection[1][0] = y2;
                                rangeForDirection[1][1] = x2;
                                
                            }else{
                                
                                rangeForDirection[1][0] = -1;
                                rangeForDirection[1][1] = -1;
                                
                                y2 = -1;
                                x2 = -1;
                                
                            }
                           
                            
                            rangeForDirection[0][0] = y1;
                            rangeForDirection[0][1] = x1;
                            
                            
                            
                            
                            if(map[y1][x1] != 'W'){
                                
                                
                                if(map[y1][x1] != 'B'){
                                    
                                    
                                    
                                    if((y2 == -1 && x2 == -1) || map[y2][x2] == 'W'){
                                        
                                        map[y1][x1] = explosionChar;
                                                                               
                                        
                                    }else{
                                        
                                        map[y1][x1] = explosionChar;
                                        map[y2][x2] = explosionChar;
                                        
                                    }
                                    
                                    
                                    
                                    
                                    
                                    
                                }else{
                                    
                                    map[y1][x1] = explosionChar;
                                    
                                    rangeForDirection[1][0] = -1;
                                    rangeForDirection[1][1] = -1;
                                    
                                    
                                }
                                
                                
                                
                            }else{
                                
                                rangeForDirection[0][0] = -1;
                                rangeForDirection[0][1] = -1;
                                
                                rangeForDirection[1][0] = -1;
                                rangeForDirection[1][1] = -1;
                                
                            }
                            
                            
                }else{
                    
                      rangeForDirection[0][0] = -1;
                      rangeForDirection[0][1] = -1;
                      
                      rangeForDirection[1][0] = -1;
                      rangeForDirection[1][1] = -1;
                    
                }
                
                
                explodeRange.add(rangeForDirection);
        
            }
    
    
    
    /**
     * Used to place the Bomb in Map at given positions
     * @param i Represents the X-axis position of Bomb
     * @param j Represents the Y-axis position of Bomb
     */
     public void placeBomb(int i, int j){
        
        
                              isExploded = false;
                              bombX = j;
                              bombY = i;

                              App.bombAnimation.canChangeFrame = true;
                              App.bombAnimation.currentFrameNo = 0;
                         
                              
                              
                              new Timer().schedule(new TimerTask(){
                              
                                  
                                  @Override
                                  public void run(){
                                      
                                     
                                      canExplode = true;
                                      
                                      
                                      
                                  }
                              
                              }, BOMB_TIME);
                              
                              isPlaced = false;
        
        
    }
     
     
     
         /**
          * Used to Remove the explosion effect after 500 milliseconds of explosion
          */
         public void removeExplosionEffectAfterExplosion(){
        
        
           if (canExplode) {
            explode(bombX, bombY, App.map);
            canExplode = false;

            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {

                    //this code will run after explosion
                    isExploded = true;
                    
                    if (explodeRange.size() > 0) {

                        App.map[bombY][bombX] = ' ';

                    }

                    for (int l = 0; l < explodeRange.size(); l++) {

                        int[][] rangeDirection = explodeRange.get(l);

                        if (rangeDirection[0][0] != -1 && rangeDirection[0][1] != -1) {
                            if (rangeDirection[0][0] == App.GOAL_INDEX[0] && rangeDirection[0][1] == App.GOAL_INDEX[1]) {
                                App.map[rangeDirection[0][0]][rangeDirection[0][1]] = 'G';
                            } else {
                                App.map[rangeDirection[0][0]][rangeDirection[0][1]] = ' ';
                            }
                        }

                        if (rangeDirection[1][0] != -1 && rangeDirection[1][1] != -1) {
                            if (rangeDirection[1][0] == App.GOAL_INDEX[0] && rangeDirection[1][1] == App.GOAL_INDEX[1]) {
                                App.map[rangeDirection[1][0]][rangeDirection[1][1]] = 'G';
                            } else {
                                App.map[rangeDirection[1][0]][rangeDirection[1][1]] = ' ';
                            }
                        }

                    }

                    //this will remove the bomb from map
                    bombX = -1;
                    bombY = -1;

                }

            }, EXPLOSION_TIME);

        }
        
        
    }
    
    
    
}
