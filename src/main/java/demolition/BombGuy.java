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
    
    

   
       
}
