/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gradleproject1;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import processing.core.*;
import gifAnimation.*;
//import gifAnimation.*;


/**
 * @author Ahtesham Sarwar
 */
public class Main extends PApplet{

    
    BombGuy bombGuy;
    RedEnemy redEnemy;
    YellowEnemy yellEnemy;
    boolean isGameOver;
    
    static long startTime;
    
    PImage solidw,brokenw,emptyw,goalw,life,clock;

    Gif playerUpGif,playerDownGif,playerLeftGif,playerRightGif,redUpGif,redDownGif,redLeftGif,redRightGif,
            yellowUpGif,yellowDownGif,yellowLeftGif,yellowRightGif;
    
    
    static int lives;
    static Level[] gameLevels;
    int levelIndex = 0;
   
    
    
    int redFrameCounter2 = 1;
    int yellowFrameCounter = 1;
    
    int[] x_direction = {0,0,1,-1};
    int[] y_direction = {1,-1,0,0};
    
    int[] goalIndex = new int[2];
    
    int start_y_pos_player = 80;
    int start_y_pos_red_en = 80;
    int start_y_pos_yell_en = 80;
    
    
    
    
    int yellEnDirection = 3;
    
    
    int yellAnim_direction = 3;
    
    
    
    
    /**
     * @param args the command line arguments
     * we extend this class from PApplet 
     * so that we can use powerful animations of processing
     * library
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        startTime = System.currentTimeMillis();
        
        PApplet.main(Main.class);
        
        
        
        //read config file
        String configData = readConfig();
        
        gameLevels = configureLevels(configData);
        
        
        
        
        
        
    }
    
    
    public void settings(){
        //set the size of window here
        this.size(480,480);
        
    }
    
    public void setup(){
        
        //here we need to instantiate every object which we draw on the window
        
        //gameOver
        isGameOver = false;
        
        
        //bombguy
        bombGuy = new BombGuy();
        
        //redEnemy
        redEnemy = new RedEnemy();
        
        //yellEnemy
        yellEnemy = new YellowEnemy();

        
        //walls
        solidw = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\wall\\solid.png");
        brokenw = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\broken\\broken.png");
        emptyw = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\empty\\empty.png");
        goalw = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\goal\\goal.png");
        
        //icons
        
        life = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\icons\\player.png");
        clock = loadImage(System.getProperty("user.dir")+"\\src\\main\\resources\\icons\\clock.png");
                    
        
        /*
        PLAYER GIFS FOR EACH CARDINAL DIRECTION
         */

        playerLeftGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\player\\player_left.gif" );
        playerDownGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\player\\player.gif" );
        playerUpGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\player\\player_up.gif" );
        playerRightGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\player\\player_right.gif" );
 
        
              
        playerDownGif.play();
        playerUpGif.play();
        playerLeftGif.play();
        playerRightGif.play();
        
        /*
        RED ENEMY GIFS FOR ANIMATION FOR EACH CARDINAL DIRECTION
        */
        
       
        redUpGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\red_enemy\\red_up.gif" );
        redDownGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\red_enemy\\red_down.gif" );
        redLeftGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\red_enemy\\red_left.gif" );
        redRightGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\red_enemy\\red_right.gif" );
        
       
        redUpGif.play();
        redDownGif.play();
        redLeftGif.play();
        redRightGif.play();
        
        /*
        YELLOW ENEMY GIFS FOR ANIMATION FOR EACH CARDINAL DIRECTION
        */
        
        yellowUpGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\yellow_enemy\\yellow_up.gif");
        yellowDownGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\yellow_enemy\\yellow_down.gif");
        yellowLeftGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\yellow_enemy\\yellow_left.gif");
        yellowRightGif = new Gif(this,System.getProperty("user.dir")+"\\src\\main\\resources\\yellow_enemy\\yellow_right.gif");
            
        
        yellowUpGif.play();
        yellowDownGif.play();
        yellowLeftGif.play();
        yellowRightGif.play();
            
            
            
        
    }
    
    public void draw(){
        
        
        //this method is used to draw content on the window
        //this method is called 60 times per second
        
        background(255,140,0);
        
        if(!isGameOver){
        
        fill(0);
        textSize(20);
        text(lives,190,40);
        image(life,150,16);
        
        long elapsedTime = System.currentTimeMillis() - startTime;
        
        
        
        if(elapsedTime/1000!=0){
          
          textSize(20);
          if((180 - (int)(elapsedTime/1000)) > 0)
          text((180 - (int)(elapsedTime/1000)),305,40); 
          else
              isGameOver = true;
              
        }else{
          textSize(20);
          text((180),305,40); 
        }
        
        
        
          image(clock,270,16);

          //testing level1
          Level level = gameLevels[levelIndex];
          char[][] map = level.mapArray;
          
          
          
          for(int i=0;i<13;i++){
              
              
              for(int j=0;j<15;j++){
                  
                  
                  
                  switch(map[i][j]){
                      
                      case 'W':
                          
                          image(solidw,j*32,(i*32)+64);
                          
                          break;
                          
                      
                      case 'P':

                         
                          //this condition is for changing level
                          if(i == goalIndex[0] && j == goalIndex[1]){
                              levelIndex = 1;
                              bombGuy.anim_direction = -1;
                          }
                          
                          
                          //this will change the position of player if there is empty or goal tile towards the position
                          //requested by user
                          if(bombGuy.direction == 0 || bombGuy.direction == 1){
                              //move down or up
                              
                              
                              int yDirec = y_direction[bombGuy.direction]; 
                              
                              map = bombGuy.moveAlongYAxis(i, j, yDirec , map);
                              
                              
                              
                              
                          }else if(bombGuy.direction == 2 || bombGuy.direction == 3){
                              //move right or left
                              
                              int xDirec = x_direction[bombGuy.direction]; 
                              
                              map = bombGuy.moveAlongXAxis(i, j, xDirec, map);
                              
                            
                              
                          }
                              
                                                                                                                                                                                                                                                        
                          //this will animate the player
                          
                          image(emptyw,j*32,(i*32)+64);
                                                                
                          int yPlayerPos = (abs(i-1) * 32)+ start_y_pos_player;
                                                                 
                          positionGif(bombGuy.anim_direction,j*32,yPlayerPos,playerUpGif,playerDownGif,playerLeftGif,playerRightGif);
                            
                            
                          break;
                      case ' ':   
                          
                          image(emptyw,j*32,(i*32)+64);
                          
                          break;
                          
                      case 'R':
                          
                          
                          //changing the direction of red enemy
                          
                          
                          
                          if(redFrameCounter2 == 60){
                          
                          if(redEnemy.redEnDirection == 0 || redEnemy.redEnDirection == 1){
                              
                              //down or up
                              
                                
                              
                              int yDirec = y_direction[redEnemy.redEnDirection];
                                
                              map = redEnemy.moveRandomFromYAxis(i, j, yDirec, map);
           
                          }else if(redEnemy.redEnDirection == 2 || redEnemy.redEnDirection == 3){
                              //right or left
                              
                              
                              
                              
                              
                              int xDirec = x_direction[redEnemy.redEnDirection]; 
                              
                              
                              
                              map = redEnemy.moveRandomFromXAxis(i, j, xDirec, map);
                              
                              
                          }
      
                          
                          redFrameCounter2 = 1;
                          
                          }else{
                              
                              redFrameCounter2++;
                              
                          }
                          
                          
                          
                          image(emptyw,j*32,(i*32)+64);
                          

                          //animating red enemy in right direction
                          
                          int yRedPos = (abs(i-1)*32) + start_y_pos_red_en;                                                                                             
                      
                          
                          
                          
                      positionGif(redEnemy.redAnimDirection,j*32,yRedPos,redUpGif,redDownGif,redLeftGif,redRightGif);
                               
                        
                          
                          break;
                      case 'Y':
                          
                          
                          
                          if(yellowFrameCounter == 60){
                          
                          if(yellEnemy.yellowEnDirection == 0 || yellEnemy.yellowEnDirection == 1){
                              
                              //down or up
                              
                                
                              
                              int yDirec = y_direction[yellEnemy.yellowEnDirection];
                                
                              map = yellEnemy.moveClockwiseFromYAxis(i, j, yDirec, map);
           
                          }else if(yellEnemy.yellowEnDirection == 2 || yellEnemy.yellowEnDirection == 3){
                              
                              
                              
                              //right or left
                              
                              int xDirec = x_direction[yellEnemy.yellowEnDirection]; 
                              
                              map = yellEnemy.moveClockwiseFromXAxis(i, j, xDirec, map);
                              
                              
                          }
      
                          
                          yellowFrameCounter = 1;
                          
                          }else{
                              
                              yellowFrameCounter++;
                              
                          }
                          
                          
                          
                          
                          image(emptyw,j*32,(i*32)+64);                                                                                                  
                          
                          //animated yellow in left direction
                          
                          
                            int yYellowPos = ((i-1)*32)+start_y_pos_yell_en;                                             
                                           
                              //here only for direction left (test) -> not completed
                             positionGif(yellEnemy.yellowAnimDirection,j*32,yYellowPos,yellowUpGif,yellowDownGif,yellowLeftGif,yellowRightGif);
                                  
                               
                                              
                          
                          
                          
                          
                          break;
                      case 'B':
                          
                          image(brokenw,j*32,(i*32)+64);
                          
                          break;
                      case 'G':
                          
                          goalIndex[0] = i;
                          goalIndex[1] = j;
                          image(goalw,j*32,(i*32)+64);
                        
                          break;
                   
                      
                  }
                  
              }
              
          
          
    }
          
    }else{
            
            //game Over
        fill(0);
        textSize(20);
        textAlign(CENTER);
        text("GAME OVER",240,240);
            
            
        }
            
        
    }
    
    
    private static String readConfig(){
        String data = "";
        try{
            
            File config = new File(System.getProperty("user.dir") + "\\config.json");
            Scanner scanner = new Scanner(config);
            
            while(scanner.hasNextLine())
                data += scanner.nextLine();
            
//            System.out.println(data);
            
           
            scanner.close();
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return data;
    }
    
    private static Level[] configureLevels(String jsonData){
        
        Level[] levelArray = null;
        
        try{
        
        JSONObject obj = new JSONObject(jsonData);
        JSONArray levels = obj.getJSONArray("levels");
        lives = obj.getInt("lives");
        
        System.out.println("Lives: " + lives);
        
        levelArray = new Level[levels.length()];
        for(int i=0;i<levels.length();i++){
            
            JSONObject levelObj = levels.getJSONObject(i);
            Level level = new Level(levelObj.getString("path"),levelObj.getInt("time"));
            
            level.setMap(readMapFromFile(level.path));
            
            levelArray[i] = level;
            System.out.println("path: " + level.path + " time: " + level.time);
            
        }
        
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return levelArray;
    }
    
    private static char[][] readMapFromFile(String path){
        
        char[][] map = new char[13][15];
        
        try{
            
            if(path != null && !path.isEmpty()){
                
                File mapFile = new File(path);
                Scanner scanner = new Scanner(mapFile);
                
                int i=0;
                while(scanner.hasNextLine()){
                
                    String row = scanner.nextLine();
                    
                    for(int j=0;j<row.length();j++){
                        
                      map[i][j] = row.charAt(j);  
                        
                    }
                
                    i++;
                }
                
               
                scanner.close();
                
                
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return map;
    }
    
    public void keyPressed(){
        
        int newdire = -1;
        if(keyCode == DOWN)
            newdire = 0;
        else if(keyCode == UP)
            newdire = 1;
        else if(keyCode == RIGHT)
            newdire = 2;
        else if(keyCode == LEFT)
            newdire = 3;
        
        if(newdire != -1)
            bombGuy.direction = bombGuy.anim_direction = newdire;
        
        
        
    }
    
    
    private void positionGif(int mDirection,int xPos,int yPos,Gif up,Gif down,Gif left,Gif right){
        
        
        switch (mDirection) {
                          
                          case -1:
                          case 0:
                              image(down,xPos,yPos);
                              break;
                          case 1:
                              image(up,xPos,yPos);
                              break; 
                          case 2:
                              image(right,xPos,yPos);
                              break;
                          case 3:
                              image(left,xPos,yPos);
                              break;
                          default:
                              break;
                      
                      }
        
        
    }
    
    
    
    
    
}
