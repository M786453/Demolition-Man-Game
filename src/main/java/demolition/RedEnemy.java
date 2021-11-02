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
    private int redFrameCounter;
    public static final int RED_OFFSET = 80;
    
    public RedEnemy(){
        
        
        redEnDirection = 0;
        redAnimDirection = 0;
        redFrameCounter = 1;
        
    }
    
    
    
     public char[][] movement(int i,int j,int direc,char[][] map){       
        
        
        if(redEnDirection != -1){
        
        char nextChar; // Represents the item at the next movement position
        int xPos;
        int yPos;
        
        if(redEnDirection == 0 || redEnDirection == 1){
            //down or up
            
            xPos = j;
            yPos = i + direc;
            nextChar = map[yPos][xPos];
            
        }else{
            //right or left
           
            xPos = j + direc;
            yPos = i;
            nextChar = map[yPos][xPos];
            
        }
        
        
         

        if ((xPos >= 0 && xPos < Map.COLUMNS) && (yPos >= 0 && yPos < Map.ROWS)) {

            
            if (nextChar == 'B' || nextChar == 'W' || (yPos == App.bomb.bombY && xPos == App.bomb.bombX)) {                                             
                
                map = moveRandom(i,j,map);
                

            }else if (nextChar == ' ' || nextChar == 'G') {

                //replaace enemy index in map array   
                
                map[yPos][xPos] = 'R';
                
                
                if(i == App.goalIndex[0] && j == App.goalIndex[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[yPos][xPos] == 'Y'){
            
                //yellow and red enemies can pass through each other
                
                map[yPos][xPos] = 'X'; //here X symbol means that yellow and read enemy are at same position
                map[i][j] = ' ';
            
            }else if(nextChar == 'P'){

                map[yPos][xPos] = 'R';
                map[i][j] = ' ';
            
            }else if(nextChar == 'H'
                                || nextChar == 'V' ||  nextChar == 'C'){
                
                //die if caught in explosion
                
                map[i][j] = ' ';
                
                
            }

        }
        
        
    }
        
        
        return map;
        
    }
    
    
    private char[][] moveRandom(int i,int j,char[][] map){
        
        
        
        int pX = -1;
        int pY = -1;
        
        ArrayList<Integer[]> openWays = new ArrayList<>(); //this list will contain positions of empty tiles around redEnemy
        
        
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
    
    
    
    public void controller(int i,int j){
        
        
          //changing the direction of red enemy
            if (redFrameCounter == 60) {

            if (redEnDirection == 0 || redEnDirection == 1) {

                //down or up
                int yDirec = App.y_direction[redEnDirection];

                App.map = movement(i, j, yDirec, App.map);

            } else if (redEnDirection == 2 || redEnDirection == 3) {
                //right or left

                int xDirec = App.x_direction[redEnDirection];

                App.map = movement(i, j, xDirec, App.map);

            }

            redFrameCounter = 1;

        } else {

            redFrameCounter++;

        }

        
    }
    
    
}
