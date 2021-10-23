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
public class RedEnemy{
    
    public int redEnDirection;
    public int redAnimDirection;
    
    public RedEnemy(){
        
        
        redEnDirection = 0;
        redAnimDirection = 0;
        
    }
    
    
    
    public char[][] moveRandomFromXAxis(int i,int j,int xDirec,char[][] map){
        
        
        
        
//        int moveIndex = j + x_direction[redEnDirection];
          int moveIndex = j + xDirec;

          
          
        if (moveIndex >= 0 && moveIndex < 15) {
            
         if(map[i][moveIndex] == 'B' || map[i][moveIndex] == 'W' || (i == App.bomb.bombY && moveIndex == App.bomb.bombX)) {
                
                
                
                map = moveRandom(i,j,map);

            } else if (map[i][moveIndex] == ' ' || map[i][moveIndex] == 'G') {


                
                //replaace enemy index in map array   
                
                map[i][moveIndex] = 'R';
                
                
                if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[i][moveIndex] == 'Y'){
            
                
                map[i][moveIndex] = 'X';
                
                map[i][j] = ' ';

            
            
            }else if(map[i][moveIndex] == 'P'){
                
//                map[1][1] = 'P';
//                App.lives--;
                App.canResetLevel = true;
                
                map[i][moveIndex] = 'R';
                map[i][j] = ' ';
                
                
            }

        }
        
        return map;
    }
    
    
    public char[][] moveRandomFromYAxis(int i,int j,int yDirec,char[][] map){
        
        
            
        
        
          int moveIndex = i + yDirec;

        if (moveIndex >= 0 && moveIndex < 14) {
            
            
            if(map[moveIndex][j] == 'B' || map[moveIndex][j] == 'W'  || (moveIndex == App.bomb.bombY && j == App.bomb.bombX)) {
                
               
                
                map = moveRandom(i,j,map);

            }else if (map[moveIndex][j] == ' ' || map[moveIndex][j] == 'G') {


                //replaace enemy index in map array   
                
                map[moveIndex][j] = 'R';
                
                
                
                
                if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[moveIndex][j] == 'Y'){
            
            map[moveIndex][j] = 'X';
                map[i][j] = ' ';

            
            
            }else if(map[moveIndex][j] == 'P'){
            
//                map[1][1] = 'P';
//                App.lives--;
                App.canResetLevel = true;
                
                map[moveIndex][j] = 'R';
                map[i][j] = ' ';
            
            }

        }
        
        
        return map;
    }
    
    
    
    private char[][] moveRandom(int i,int j,char[][] map){
        
        
        
        int pX = -1;
        int pY = -1;
        
        ArrayList<Integer[]> openWays = new ArrayList<Integer[]>(); //this list will contain positions of empty tiles around redEnemy
        
        
        if(!(i+1 == App.bomb.bombY && j == App.bomb.bombX))
        if((map[i+1][j] == ' ' || map[i+1][j] == 'Y')){
            Integer[] x_y = new Integer[2];
            x_y[0] = i+1;
            x_y[1] = j;
            openWays.add(x_y);
            
        }else if(map[i+1][j] == 'P'){
            pX = i+1;
            pY = j;
        }
        
        
        
        if(!(i-1 == App.bomb.bombY && j == App.bomb.bombX))
        if(map[i-1][j] == ' ' || map[i-1][j] == 'Y'){
            Integer[] x_y = new Integer[2];
            x_y[0] = i-1;
            x_y[1] = j;
            openWays.add(x_y);
            
        }else if(map[i-1][j] == 'P'){
            pX = i-1;
            pY = j;
        }
        
        
        if(!(i == App.bomb.bombY && j+1 == App.bomb.bombX))
            if(map[i][j+1] == ' ' || map[i][j+1] == 'Y'){
                Integer[] x_y = new Integer[2];
                x_y[0] = i;
                x_y[1] = j+1;
                openWays.add(x_y);
            
            }else if(map[i][j+1] == 'P'){
                pX = i;
                pY = j+1;
            }
        
        
        if(!(i == App.bomb.bombY && j-1 == App.bomb.bombX))
            if(map[i][j-1] == ' ' || map[i][j-1] == 'Y'){
                Integer[] x_y = new Integer[2];
                x_y[0] = i;
                x_y[1] = j-1;
                openWays.add(x_y);
            
            }else if(map[i][j-1] == 'P'){
                pX = i;
                pY = j-1;
            }
        
        
        if(openWays.size() > 0){
            
            Random random = new Random();
            int randIndex = random.nextInt(openWays.size());
            
            Integer[] randOpenWay = openWays.get(randIndex);
            
            int newX = randOpenWay[0];
            int newY = randOpenWay[1];
            
            if(map[newX][newY] == 'Y')
                map[newX][newY] = 'X';
            else if(map[newX][newY] == ' ')
                map[newX][newY] = 'R';
            
            if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';
            
            //will set the direction and anim direction of red enemy according to new positions
            setRedDirection(newX,newY,i,j);
            
           
            
        }else{
            
            
            if(pX != -1 && pY != -1){
                
//                map[1][1] = 'P';
//                App.lives--;
                App.canResetLevel = true;
                
                map[pX][pY] = 'R';
                map[i][j] = ' ';
                
                setRedDirection(pX,pY,i,j);
                
                
            }else
                System.out.println("No path found for redEnemy");
                          
        }
        
      
                  

        return map;
    }
    
    
    private void setRedDirection(int newX,int newY,int oldX,int oldY){
        
         if(newX > oldX)
                redEnDirection = redAnimDirection = 0;
            else if(newX < oldX)
                redEnDirection = redAnimDirection = 1;
            else if(newY > oldY)
                redEnDirection = redAnimDirection = 2;
            else if(newY < oldY)
                redEnDirection = redAnimDirection = 3;
        
    }
    
    
}
