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
public class RedEnemy{
    
    public int redEnDirection;
    public int redAnimDirection;
    
    public RedEnemy(){
        
        
        redEnDirection = 2;
        redAnimDirection = 2;
        
    }
    
    
    
    public char[][] moveRandomFromXAxis(int i,int j,int xDirec,char[][] map){
        
        
//        int moveIndex = j + x_direction[redEnDirection];
          int moveIndex = j + xDirec;

        if (moveIndex >= 0 && moveIndex < 14) {

            if (map[i][moveIndex] == ' ') {


                //replaace enemy index in map array   
                
                map[i][moveIndex] = 'R';
                map[i][j] = ' ';
//                                      redEnDirection = 2;

            } else if (map[i][moveIndex] == 'B' || map[i][moveIndex] == 'W') {

                ArrayList<Integer> cardinalPos = new ArrayList<Integer>();

                cardinalPos.add(i + 1);
                cardinalPos.add(i - 1);
                cardinalPos.add(j + 1);
                cardinalPos.add(j - 1);


                int nextMove = -1;
                while (nextMove < 0) {

                    Random rand = new Random();


                    if (cardinalPos.size() > 0) {

                        int randIndex = rand.nextInt(cardinalPos.size());

                      
                        
                        if (cardinalPos.get(randIndex) == -1) {
                            break;
                        }


                        if (randIndex == 0 || randIndex == 1) {
                            //down or up

                            if (cardinalPos.get(randIndex) < 13) {


                                if (map[cardinalPos.get(randIndex)][j] == ' ') {

                                    nextMove = cardinalPos.get(randIndex);

                                    //replaace enemy index in map array   
                                    
                                    map[nextMove][j] = 'R';
                                    map[i][j] = ' ';

                                    redEnDirection = randIndex;
                                    redAnimDirection = redEnDirection;
//                                                System.out.println("Red Direction: " + redEnDirection);

                                    break;

                                } else {
                                    cardinalPos.set(randIndex, -1);
                                }

                            } else {

                                cardinalPos.set(randIndex, -1);

                            }

                        } else if (randIndex == 2 || randIndex == 3) {
                            //right or left
                            if (cardinalPos.get(randIndex) < 14) {


                                if (map[i][cardinalPos.get(randIndex)] == ' ') {


                                    nextMove = cardinalPos.get(randIndex);

                                    //replaace enemy index in map array   
                                    
                                    map[i][nextMove] = 'R';
                                    map[i][j] = ' ';

                                    redEnDirection = randIndex;
                                    redAnimDirection = redEnDirection;


                                    break;

                                } else {

                                    cardinalPos.set(randIndex, -1);

                                }

                            } else {

                                cardinalPos.set(randIndex, -1);

                            }

                        }

                    }

                }

            }

        }
        
        return map;
    }
    
    
    public char[][] moveRandomFromYAxis(int i,int j,int yDirec,char[][] map){
        
        
        
        
          int moveIndex = i + yDirec;

        if (moveIndex >= 0 && moveIndex < 13) {

            if (map[moveIndex][j] == ' ') {


                //replaace enemy index in map array   
                
                map[moveIndex][j] = 'R';
                map[i][j] = ' ';
//                                      redEnDirection = 2;

            } else if (map[moveIndex][j] == 'B' || map[moveIndex][j] == 'W') {

                ArrayList<Integer> cardinalPos = new ArrayList<Integer>();

                cardinalPos.add(i + 1);
                cardinalPos.add(i - 1);
                cardinalPos.add(j + 1);
                cardinalPos.add(j - 1);


                int nextMove = -1;
                while (nextMove < 0) {

                    Random rand = new Random();


                    if (cardinalPos.size() > 0) {

                        int randIndex = rand.nextInt(cardinalPos.size());

                     
                        
                        if (cardinalPos.get(randIndex) == -1) {
                            break;
                        }


                        if (randIndex == 0 || randIndex == 1) {
                            //down or up

                            if (cardinalPos.get(randIndex) < 13) {


                                if (map[cardinalPos.get(randIndex)][j] == ' ') {

                                    nextMove = cardinalPos.get(randIndex);

                                    //replaace enemy index in map array   
                                    
                                    map[nextMove][j] = 'R';
                                    map[i][j] = ' ';

                                    redEnDirection = randIndex;
                                    redAnimDirection = redEnDirection;


                                    break;

                                } else {
                                    cardinalPos.set(randIndex, -1);
                                }

                            } else {

                                cardinalPos.set(randIndex, -1);

                            }

                        } else if (randIndex == 2 || randIndex == 3) {
                            //right or left
                            if (cardinalPos.get(randIndex) < 14) {


                                if (map[i][cardinalPos.get(randIndex)] == ' ') {


                                    nextMove = cardinalPos.get(randIndex);

                                    //replaace enemy index in map array   
                                    
                                    map[i][nextMove] = 'R';
                                    map[i][j] = ' ';

                                    redEnDirection = randIndex;
                                    redAnimDirection = redEnDirection;


                                    break;

                                } else {

                                    cardinalPos.set(randIndex, -1);

                                }

                            } else {

                                cardinalPos.set(randIndex, -1);

                            }

                        }

                    }

                }

            }

        }
        
        
        return map;
    }
    
}
