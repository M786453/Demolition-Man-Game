/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;



import java.util.ArrayList;



/**
 *
 * @author Ahtesham Sarwar
 */
public class BombGuy{
    
    public static int lives = 0;
    public int direction;
    public int anim_direction;
    public boolean isPlayerInMap = false;
    public final int PLAYER_OFFSET = 80;
    
    
    public static ArrayList<Integer[]> pStartPlayerPosLevelList = new ArrayList<>();
    
    
    public BombGuy(){
        
        direction = -1;
        anim_direction = -1;
        
      
    }
    
    public char[][] movement(int i,int j,int direc,char[][] map){
        
        
        if(direction != -1){
        
        char nextChar; // Represents the item at the next movement position
        int xPos;
        int yPos;

        if (direction == 0 || direction == 1) {
            //down or up

            xPos = j;
            yPos = i + direc;
            nextChar = map[yPos][xPos];

        } else {
            //right or left

            xPos = j + direc;
            yPos = i;
            nextChar = map[yPos][xPos];

        }

        if ((xPos >= 0 && xPos < Map.COLUMNS) && (yPos >= 0 && yPos < Map.ROWS)) {

            if (nextChar == ' ' || nextChar == 'G') {
                //replaace player index in map array   
                if (!(yPos == Bomb.bombY && xPos == Bomb.bombX)) {

                    map[yPos][xPos] = 'P';
                    map[i][j] = ' ';
                    direction = -1;

                }
            } else if (nextChar == 'R' || nextChar == 'Y' || nextChar == 'H'
                    || nextChar == 'V' || nextChar == 'C') {

                //player will die if this condition fullfils
                map[i][j] = ' ';
                direction = -1;

            }

        }
        
        }
                              
        return map;
        
    }
    
    

    public void playerDieAndResetLevel() {

        if (Bomb.isExploded) {

            /*
                      Here checking if any bomb is placed and exploded then show the player in the map
                      This variable will be if the bomb is not placed
                      In this way this piece of will be called in other cases Like when player
                      comes in contact with any enemy
                      
                      NOTE: Here the bomb.isExploded is only false when bomb is placed and it will be true
                      after explosion or even when bomb is not placed. So player will lose life and level will reset when in
                      contact with explosion or an enemy.
             */
            App.gameLevels.get(App.levelIndex).resetLevel();

            Integer[] playerLevelStartPos = pStartPlayerPosLevelList.get(App.levelIndex);

            App.map[playerLevelStartPos[0]][playerLevelStartPos[1]] = 'P';

            App.bombGuy = new BombGuy();
            
            BombGuy.lives--;

            App.redEnemy = new RedEnemy();
            App.yellEnemy = new YellowEnemy();

            isPlayerInMap = true;

        }

    }
    
    
    
        public void controlPlayer(int i, int j){
        
        
        //this will change the position of player if there is empty or goal tile towards the position
        
                          //requested by user
                          if(direction == 0 || direction == 1){
                              //move down or up
                              
                              
                              int yDirec = App.y_direction[direction]; 
                              
                              App.map = movement(i, j, yDirec , App.map);
                              
                              
                              
                              
                          }else if(direction == 2 || direction == 3){
                              //move right or left
                              
                              int xDirec = App.x_direction[direction]; 
                              
                              App.map = movement(i, j, xDirec, App.map);
                              
                            
                          }
        
        
    }
        
        
        public void changeLevelIfPlayerAtGoal(int i, int j){
        
        /*
            If Player at Goal Position, change the level. If Player
            Goal Tile of last level , then player will win game
        */
        
                          if(i == App.goalIndex[0] && j == App.goalIndex[1]){
                              
                              if(App.levelIndex == App.gameLevels.size()-1)
                                  App.isWin = true;
                              else
                                  App.levelIndex++;
                              
                              App.startTime = System.currentTimeMillis();
                              
                                  anim_direction = -1;
                          }
        
        
    }
   
       
}
