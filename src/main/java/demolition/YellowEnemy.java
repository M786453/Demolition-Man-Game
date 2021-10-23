/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

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

        if (moveIndex >= 0 && moveIndex < 15) {

            
            if (map[i][moveIndex] == 'B' || map[i][moveIndex] == 'W' || (i == App.bomb.bombY && moveIndex == App.bomb.bombX)) {                                             
                
                map = moveClockwise(i,j,map);
                

            }else if (map[i][moveIndex] == ' ' || map[i][moveIndex] == 'G') {

                

                //replaace enemy index in map array   
                
                map[i][moveIndex] = 'Y';
                
                
                if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[i][moveIndex] == 'R'){
            
                //yellow and red enemies can pass through each other
                
                map[i][moveIndex] = 'X'; //here X symbol means that yellow and read enemy are at same position
                map[i][j] = ' ';
            
            }else if(map[i][moveIndex] == 'P'){
            
//                map[1][1] = 'P';
                App.lives--;
                App.canResetLevel = true;
                
                map[i][moveIndex] = 'Y';
                map[i][j] = ' ';
            
            }

        }
        
        
        
        return map;
        
    }

    
    public char[][] moveClockwiseFromYAxis(int i,int j,int yDirec,char[][] map){
        
        
        
         int moveIndex = i + yDirec;

        if (moveIndex >= 0 && moveIndex < 14) {

            if (map[moveIndex][j] == 'B' || map[moveIndex][j] == 'W' || (moveIndex == App.bomb.bombY && j == App.bomb.bombX)) {

                
                map = moveClockwise(i,j,map);
                            

            }else if (map[moveIndex][j] == ' ' || map[moveIndex][j] == 'G') {


                //replaace enemy index in map array   
                
                map[moveIndex][j] = 'Y';
                
                
                if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[moveIndex][j] == 'R'){
            
                
                map[moveIndex][j] = 'X';
                map[i][j] = ' ';

                
            
            }else if(map[moveIndex][j] == 'P'){
            
//                map[1][1] = 'P';
                App.lives--;
                App.canResetLevel = true;
            
                map[moveIndex][j] = 'Y';
                map[i][j] = ' ';
                
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
                        
//                        System.out.println("BOMB POSITION: " + "Y: " + App.bomb.bombY + "X: " + App.bomb.bombX );
//                        System.out.println("Down/up: " + "Y: " + cardinalPos.get(k) + "X: " + j );
                        
                        if(!(cardinalPos.get(k) == App.bomb.bombY && j == App.bomb.bombX))
                        if(map[cardinalPos.get(k)][j] == ' '){
                            
                            map[cardinalPos.get(k)][j] = 'Y';
                            
                            if(i == App.goalIndex[0] && j == App.goalIndex[1])
                                map[i][j] = 'G';
                            else
                                map[i][j] = ' ';
                            
                            yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                            break;
                        }else if(map[cardinalPos.get(k)][j] == 'R'){
                        
                            map[cardinalPos.get(k)][j] = 'X';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                            break;
                        
                        }else if(map[cardinalPos.get(k)][j] == 'P'){
                            
//                             map[1][1] = 'P';
                             App.lives--;
                             App.canResetLevel = true;
                            
                             map[cardinalPos.get(k)][j] = 'Y';
                             map[i][j] = ' ';
                             yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                             break;
                            
                        }
                                
                  }else{
                        
                        
//                        System.out.println("BOMB POSITION: " + "Y: " + App.bomb.bombY + "X: " + App.bomb.bombX );
//                        System.out.println("Down/up: " + "Y: " + i + "X: " + cardinalPos.get(k) );
                        
                        if(!(i == App.bomb.bombY && cardinalPos.get(k) == App.bomb.bombX))
                        if(map[i][cardinalPos.get(k)] == ' '){
                            
                            map[i][cardinalPos.get(k)] = 'Y';
                            
                            if(i == App.goalIndex[0] && j == App.goalIndex[1])
                                    map[i][j] = 'G';
                            else
                                    map[i][j] = ' ';
                            
                            yellowAnimDirection = yellowEnDirection = getDirection(k);
                            break;
                        }else if(map[i][cardinalPos.get(k)] == 'R'){
                        
                            map[i][cardinalPos.get(k)] = 'X';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k);
                            break;
                        
                        
                        }else if(map[i][cardinalPos.get(k)] == 'P'){
                            
//                            map[1][1] = 'P';
                            App.lives--;
                            App.canResetLevel = true;
                            
                            
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
