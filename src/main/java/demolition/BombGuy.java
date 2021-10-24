/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

import processing.core.PApplet;

/**
 *
 * @author Ahtesham Sarwar
 */
public class BombGuy{
    
    public int lives;
    public int direction;
    public int anim_direction;
    
    
    
    
    
    public BombGuy(){
        
//        lives = 3;
        direction = -1;
        anim_direction = -1;
      
    }
    
    public char[][] moveAlongXAxis(int i,int j,int x_direc,char[][] map){
        //move left or right
            int moveIndex = j+x_direc; 
                              
                       if(moveIndex >= 0){
                                 
                           if(map[i][moveIndex] == ' ' || map[i][moveIndex] == 'G'){
                               //replaace player index in map array   
                               if(!(i == App.bomb.bombY && moveIndex == App.bomb.bombX)){
                                   
                               map[i][moveIndex] = 'P';
                               map[i][j] = ' ';
                               direction = -1;
                               
                               }
                        }else if(map[i][moveIndex] == 'R' || map[i][moveIndex] == 'Y' || map[i][moveIndex] == 'H'
                                || map[i][moveIndex] == 'V' || map[i][moveIndex] == 'J' || map[i][moveIndex] == 'K'
                                || map[i][moveIndex] == 'L' || map[i][moveIndex] == 'M' || map[i][moveIndex] == 'C'){
                            
                            
//                           map[1][1] = 'P';
//                           App.lives--;
                           App.canResetLevel = true;
                           
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
                       if(!(moveIndex == App.bomb.bombY && j == App.bomb.bombX)){
                       
                           map[moveIndex][j] = 'P';
                           map[i][j] = ' ';
                           direction = -1;
                                      
                       }
                       
                       
                       }else if(map[moveIndex][j] == 'R' || map[moveIndex][j] == 'Y' || map[moveIndex][j] == 'H'
                                || map[moveIndex][j] == 'V' || map[moveIndex][j] == 'J' || map[moveIndex][j] == 'K'
                                || map[moveIndex][j] == 'L' || map[moveIndex][j] == 'M' || map[moveIndex][j] == 'C'){
                           
//                           map[1][1] = 'P';
//                           App.lives--;
                           App.canResetLevel = true;
                           
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
