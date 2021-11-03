/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;


import java.util.ArrayList;

/**
 * Represents Yellow Enemy Object of Game
 * @author Ahtesham Sarwar
 */
public class YellowEnemy {
    
    /**
     * Represents the Direction of motion of Yellow Enemy
     */
    public int yellowEnDirection;
    
    /**
     * Represents the Direction of Animation of Yellow Enemy
     */
    public int yellowAnimDirection;
    
    /**
     * Counts the number of times controller function called by draw function of main class
     */
    public int yellowFrameCounter;
    
    /**
     * Represents the space between Yellow Enemy and the window
     */
    public static final int YELLOW_OFFSET = 80;    
    
    public YellowEnemy(){
    
        yellowEnDirection = 0;
        yellowAnimDirection = 0;
        yellowFrameCounter = 1;
    
    }
    
    
    
    /**
     * Use for the movement of Yellow Enemy
     * @param i Represents the Y-axis Position of Yellow Enemy
     * @param j Represents the X-axis Position of Yellow Enemy
     * @param direc Represents the direction of motion of Yellow Enemy
     * @param map Represents the map of current level 
     * @return Return a modified map (2-D Char Array)
     */
    public char[][] movement(int i,int j,int direc,char[][] map){       
        
        
        if(yellowEnDirection != -1){
        
        char nextChar; // Represents the item at the next movement position
        int xPos;
        int yPos;
        
        if(yellowEnDirection == 0 || yellowEnDirection == 1){
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

            
            if (nextChar == 'B' || nextChar == 'W' || (yPos == Bomb.bombY && xPos == Bomb.bombX)) {                                             
                
                map = moveClockwise(i,j,map);
                

            }else if (nextChar == ' ' || nextChar == 'G') {

                //replaace enemy index in map array   
                
                map[yPos][xPos] = 'Y';
                
                
                if(i == App.GOAL_INDEX[0] && j == App.GOAL_INDEX[1])
                    map[i][j] = 'G';
                else
                    map[i][j] = ' ';


            }else if(map[yPos][xPos] == 'R'){
            
                //yellow and red enemies can pass through each other
                
                map[yPos][xPos] = 'X'; //here X symbol means that yellow and read enemy are at same position
                map[i][j] = ' ';
            
            }else if(nextChar == 'P'){

                map[yPos][xPos] = 'Y';
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

    /**
     * Use for the clockwise movement of Yellow Enemy if collide with wall or broken wall
     * @param i Represent the Y-axis Position of Yellow Enemy
     * @param j Represent the X-axis Position of Yellow Enemy
     * @param map Represent the map of current level
     * @return Return a modified map (2-D Char Array)
     */
    private char[][] moveClockwise(int i,int j,char[][] map){
        
        
                ArrayList<Integer> cardinalPos = new ArrayList<>();

                //these cardinal positions are arranged in clockwise pattern of positions
                    
                cardinalPos.add(i + 1); // down 
                
                cardinalPos.add(j - 1); //left
                
                cardinalPos.add(i - 1); //up                              
                
                cardinalPos.add(j + 1); //right
     
                
                int lDirection = getClockwiseDirection();
                int lDirection2 = lDirection; //duplicating the lDirection variable for later use
                
                if(lDirection != -1){

                    lDirection += 1;
                    
                if(lDirection > 3)
                    lDirection = 0;
                
                for(int k=lDirection;k < 4;k++){
                    
                    char nextChar; // Represent the item present in map for the current clockwise position
                    int xPos;
                    int yPos;
                    
                    if(k == 0 || k == 2){
                        nextChar = map[cardinalPos.get(k)][j];
                        xPos = j;
                        yPos = cardinalPos.get(k);
                    }else{                        
                        nextChar = map[i][cardinalPos.get(k)];
                        xPos =  cardinalPos.get(k);
                        yPos =  i;
                    }
                        
                        
                        if(!(cardinalPos.get(k) == Bomb.bombY && j == Bomb.bombX))
                        if(nextChar == ' '){
                            
                            map[yPos][xPos] = 'Y';
                            
                            if(i == App.GOAL_INDEX[0] && j == App.GOAL_INDEX[1])
                                map[i][j] = 'G';
                            else
                                map[i][j] = ' ';
                            
                            yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                            break;
                        }else if(nextChar == 'R'){
                        
                            map[yPos][xPos] = 'X';
                            map[i][j] = ' ';
                            yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                            break;
                        
                        }else if(nextChar == 'P'){
                       
                             map[yPos][xPos] = 'Y';
                             map[i][j] = ' ';
                             yellowAnimDirection = yellowEnDirection = getDirection(k); //this will get the direction according to original directions pattern from clockwsie direction value
                            
                             break;
                            
                        }else if(nextChar == 'H'
                                || nextChar == 'V' || nextChar == 'C'){
                            
                            
                            map[i][j] = ' ';
                            
                            break;
                            
                        }
                                
                  
                    if(lDirection+1 == lDirection2)
                        break;
                        
                 
                }                               
                          
    }
                
        return map;
    }
    
    
    
    private int getClockwiseDirection(){
        
         int lDirection = -1;
                
                /*
                        Here lDirection will act as index of above cardinalPos list which actually
                        contains the surrounding possible movement positions of yellow enemy in circular pattern: 
                            Down -> Left -> Up -> Right 
                indexes:     0   ->  1   -> 2  ->   3
                        Where as yellowEnDirection Variable contains the current direction of yellow Enemy.
                        Here one thing to note is lDirection is the index for cardinalPos list not the actual direction.
                        On the other hand, yellowEnDirection is the actual direction not an index.
                        */
                
                switch(yellowEnDirection){
                    
                    case 0: //Represents Down Direction
                       lDirection = 0;
                       break;
                    case 1: //Represents Up Direction
                        lDirection = 2;
                        break;
                    case 2: //Represents Right Direction
                        lDirection = 3;
                        break;
                    case 3: //Represents Left Direction
                        lDirection = 1;
                        break;
                    
                    }
       
                return lDirection;
    }
    
    
    private int getDirection(int index){
        
        int lDirection = -1;
                
                /*
                
                This method will retrieve the actual direction from the variable used as index of 
                cardinalPos list ( list in circular pattern of positions)
        
                */
                
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
    
   /**
    * Interface to control the movement of Yellow Enemy
    * @param i Represents the Y-axis Position of Yellow Enemy
    * @param j Represents the X-axis Position of Yellow Enemy
    */ 
   public void controller(int i,int j){
       
    
       if (yellowFrameCounter == 60) {

            if (yellowEnDirection == 0 || yellowEnDirection == 1) {

                //down or up
                int yDirec = App.Y_DIRECTION[yellowEnDirection];

                App.map = movement(i, j, yDirec, App.map);

            } else if (yellowEnDirection == 2 || yellowEnDirection == 3) {

                //right or left
                int xDirec = App.X_DIRECTION[yellowEnDirection];

                App.map = movement(i, j, xDirec, App.map);

            }

            yellowFrameCounter = 1;

        } else {

            yellowFrameCounter++;

        }
       
       
       
   }
    
    
    
}
