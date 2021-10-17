/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gradleproject1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ahtesham Sarwar
 */
public class YellowEnemy {
    
    
    
    public int yellowEnDirection;
    public int yellowAnimDirection;
    
    
    public YellowEnemy(){
    
        yellowEnDirection = 0;
        yellowAnimDirection = 0;
    
    
    
    }
    
    
    public char[][] moveClockwiseFromXAxis(int i,int j,int xDirec,char[][] map){                
        
         int moveIndex = j + xDirec;

        if (moveIndex >= 0 && moveIndex < 14) {

            if (map[i][moveIndex] == ' ') {


                //replaace enemy index in map array   
                
                map[i][moveIndex] = 'Y';
                map[i][j] = ' ';


            }else if(map[i][moveIndex] == 'P'){
            
                map[1][1] = 'P';
                Main.lives--;
                Main.canResetLevel = true;
                
                map[i][moveIndex] = 'Y';
                map[i][j] = ' ';
            
            }else if (map[i][moveIndex] == 'B' || map[i][moveIndex] == 'W') {                                             
                
                map = moveClockwise(i,j,map);
                

            }

        }
        
        
        
        return map;
        
    }

    
    public char[][] moveClockwiseFromYAxis(int i,int j,int yDirec,char[][] map){
        
        
        
         int moveIndex = i + yDirec;

        if (moveIndex >= 0 && moveIndex < 13) {

            if (map[moveIndex][j] == ' ') {


                //replaace enemy index in map array   
                
                map[moveIndex][j] = 'Y';
                map[i][j] = ' ';


            }else if(map[moveIndex][j] == 'P'){
            
                map[1][1] = 'P';
                Main.lives--;
                Main.canResetLevel = true;
            
                map[moveIndex][j] = 'Y';
                map[i][j] = ' ';
                
            } else if (map[moveIndex][j] == 'B' || map[moveIndex][j] == 'W') {

                
                map = moveClockwise(i,j,map);
                            

            }

        }
        
        
        return map;
    }
    
    private char[][] moveClockwise(int i,int j,char[][] map){
        
        
                ArrayList<Integer> cardinalPos = new ArrayList<Integer>();

                    //these cardinal positions are arranged in clockwise pattern
                    
                cardinalPos.add(i + 1); // down 
                
                cardinalPos.add(j - 1); //left
                
                cardinalPos.add(i - 1); //up                              
                
                cardinalPos.add(j + 1); //right
     
               
                
                
                int lDirection = getClockwiseDirection();
                int lDirection2 = lDirection;
                
                if(lDirection != -1){

                    lDirection += 1;
                    
                if(lDirection > 3)
                    lDirection = 0;
                
                for(int k=lDirection;k < 4;k++){
                    
                    if(k == 0 || k == 2){
                        
                        if(map[cardinalPos.get(k)][j] == ' '){
                            
                            map[cardinalPos.get(k)][j] = 'Y';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                            break;
                        }else if(map[cardinalPos.get(k)][j] == 'P'){
                            
                             map[1][1] = 'P';
                             Main.lives--;
                             Main.canResetLevel = true;
                            
                             map[cardinalPos.get(k)][j] = 'Y';
                             map[i][j] = ' ';
                             yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                             break;
                            
                        }
                                
                  }else{
                        
                        
                        if(map[i][cardinalPos.get(k)] == ' '){
                            
                            map[i][cardinalPos.get(k)] = 'Y';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k);
                            break;
                        }else if(map[i][cardinalPos.get(k)] == 'P'){
                            
                            map[1][1] = 'P';
                            Main.lives--;
                            Main.canResetLevel = true;
                            
                            
                            map[i][cardinalPos.get(k)] = 'Y';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k);
                            break;
                           
                        }
                        
                    }
                    
                    
                    if(lDirection+1 == lDirection2)
                        break;
                        
                        
                    
                    
                }                               
                          
    }
        return map;
    }
    
    
    
    private int getClockwiseDirection(){
        
         int lDirection = -1;
                
                
                switch(yellowEnDirection){
                    
                    case 0:
                        
                       lDirection = 0;
                       break;
                    case 1:
                        lDirection = 2;
                        break;
                    case 2:
                        lDirection = 3;
                        break;
                    case 3:
                        lDirection = 1;
                        break;
                    
                    }
       
                return lDirection;
    }
    
    
    private int getDirection(int index){
        
        int lDirection = -1;
                
                
                switch(index){
                    
                    case 0:
                        
                       lDirection = 0;
                       break;
                    case 1:
                        lDirection = 3;
                        break;
                    case 2:
                        lDirection = 1;
                        break;
                    case 3:
                        lDirection = 2;
                        break;
                    
                    }
        
        return lDirection;
    }
    
    
   
    
    
    
}