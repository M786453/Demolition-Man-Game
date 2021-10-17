/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gradleproject1;

import processing.core.PApplet;

/**
 *
 * @author Ahtesham Sarwar
 */
public class BombGuy extends PApplet {
    
    public int lives;
    public int direction;
    public int anim_direction;
    
    
    
    
    public BombGuy(){
        
        lives = 3;
        direction = -1;
        anim_direction = -1;
      
    }
    
    public char[][] moveAlongXAxis(int i,int j,int x_direc,char[][] map){
        //move left or right
            int moveIndex = j+x_direc; 
                              
                       if(moveIndex >= 0){
                                 
                           if(map[i][moveIndex] == ' ' || map[i][moveIndex] == 'G'){
                               //replaace player index in map array   
                               
                               map[i][moveIndex] = 'P';
                               map[i][j] = ' ';
                               direction = -1;
                                      
                        }else if(map[i][moveIndex] == 'R' || map[i][moveIndex] == 'Y'){
                            
                            
                           map[1][1] = 'P';
                           Main.lives--;
                           Main.canResetLevel = true;
                           
                           map[i][j] = ' ';
                           direction = -1;
                            
                        }
                                  
                }
                              
        return map;
        
    }
    
    public char[][] moveAlongYAxis(int i,int j,int y_direc,char[][] map){
        //move up or down
        
          int moveIndex = i+y_direc; 
                              
                              
                              
          if(moveIndex >= 0){
                                  

                                  
            if(map[moveIndex][j] == ' ' || map[moveIndex][j] == 'G'){
                       //replaace player index in map array   
                       
                       map[moveIndex][j] = 'P';
                       map[i][j] = ' ';
                       direction = -1;
                                      
                       }else if(map[moveIndex][j] == 'R' || map[moveIndex][j] == 'Y'){
                           
                           map[1][1] = 'P';
                           Main.lives--;
                           Main.canResetLevel = true;
                           
                           map[i][j] = ' ';
                           direction = -1;
                           
                           
                       }
                                  
              }
          
       return map; 
    }
    
    
    
    
    public void placeBomb(){
        
        
        
    }
    
    
    public void die(){
        
    }
    
    public void respawn(){
        
    }
        
}
