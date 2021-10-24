/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demolition;


import java.io.File;
import java.util.Scanner;
import processing.data.*;
import processing.core.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;




/**
 * @author Ahtesham Sarwar
 */
public class App extends PApplet{

    
    private final int HEIGHT = 480;
    private final int WIDTH = 480;
    private final int FPS = 60;
    private static boolean isPlayerInMap;
    private static ArrayList<Integer[]> pStartPosLevelList;
    
    BombGuy bombGuy;
    RedEnemy redEnemy;
    YellowEnemy yellEnemy;    
    static Bomb bomb;
    boolean isGameOver;
    static boolean canResetLevel;
    boolean isWin;
    
    static long startTime;
    
    PImage solidw,brokenw,emptyw,goalw,life,clock,explosionC,explosionEndL,explosionEndR,explosionEndT,explosionEndB,explosionH,explosionV;            ;
    
    ArrayList<PImage> playerDownFramesList;
    ArrayList<PImage> playerUpFramesList;
    ArrayList<PImage> playerLeftFramesList;
    ArrayList<PImage> playerRightFramesList;
    
    
    ArrayList<PImage> redEnDownFramesList;
    ArrayList<PImage> redEnUpFramesList;
    ArrayList<PImage> redEnLeftFramesList;
    ArrayList<PImage> redEnRightFramesList;
    
    
    ArrayList<PImage> yellowEnDownFramesList;
    ArrayList<PImage> yellowEnUpFramesList;
    ArrayList<PImage> yellowEnLeftFramesList;
    ArrayList<PImage> yellowEnRightFramesList;
    
    ArrayList<PImage> bombFramesList;

    
    static int lives;
    static Level[] gameLevels;
    int levelIndex = 0;
   
    char[][] map;
    
    
    int redFrameCounter2 = 1;
    int yellowFrameCounter = 1;
    
    int[] x_direction = {0,0,1,-1};
    int[] y_direction = {1,-1,0,0};
    
    static int[] goalIndex = new int[2];
    
    int start_y_pos_player = 80;
    int start_y_pos_red_en = 80;
    int start_y_pos_yell_en = 80;
    
    
    
    
    int yellEnDirection = 3;
    
    
    int yellAnim_direction = 3;
    
    
    private Animation characterAnimation;
    
    private Animation bombAnimation;
    
    
    /**
     * @param args the command line arguments
     * we extend this class from PApplet 
     * so that we can use powerful animations of processing
     * library
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //initially value of this "isPlayerInMap" is set to false 
        //because we want to check later whether the player is present in the map or not
        
        isPlayerInMap = false;
        pStartPosLevelList = new ArrayList<>();
        
        startTime = System.currentTimeMillis();
        
        PApplet.main("demolition.App");
        
        
        
        //read config file
        String configData = readConfig();
        
        gameLevels = configureLevels(configData);
        
        
        
        
        
        
    }
    
    
    public void settings(){
        //set the size of window here
        this.size(HEIGHT,WIDTH);
        
        
    }
    
    public void setup(){
        //represents the framerate
        frameRate(FPS);
        
        
        //here we need to instantiate every object which we draw on the window
        
        //initialy canResetLevel is false
        //it will be true when player loses a life
        //when it is true the level will be reset
        
        
          
        
          playerDownFramesList = new ArrayList<>();
          playerUpFramesList = new ArrayList<>();
          playerLeftFramesList = new ArrayList<>();
          playerRightFramesList = new ArrayList<>();
        
          
          redEnDownFramesList = new ArrayList<>();
          redEnUpFramesList = new ArrayList<>();
          redEnLeftFramesList = new ArrayList<>();
          redEnRightFramesList = new ArrayList<>();
        
          
          yellowEnDownFramesList = new ArrayList<>();
          yellowEnUpFramesList = new ArrayList<>();
          yellowEnLeftFramesList = new ArrayList<>();
          yellowEnRightFramesList = new ArrayList<>();
        
          
          bombFramesList = new ArrayList<>();
          
          characterAnimation = new Animation(0.2f);
          bombAnimation = new Animation(0.25f);
          
        canResetLevel = false;
        
        
        isWin = false;
        
        
        //gameOver
        isGameOver = false;
        
        //bomb
        bomb = new Bomb();
        
        
        //bombguy
        bombGuy = new BombGuy();
        
        //redEnemy
        redEnemy = new RedEnemy();
        
        //yellEnemy
        yellEnemy = new YellowEnemy();

        
        //walls
        solidw = loadImage(System.getProperty("user.dir")+ File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "wall" + File.separator + "solid.png");
        brokenw = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "broken" + File.separator + "broken.png");
        emptyw = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "empty" + File.separator + "empty.png");
        goalw = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "goal" + File.separator + "goal.png");
        
        
        //icons
        life = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "icons" + File.separator + "player.png");
        clock = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "icons" + File.separator + "clock.png");
        
                    
        
        /*
        PLAYER IMAGES FOR EACH CARDINAL DIRECTION
         */
        
        
        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player1.png"));
        
        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player2.png"));
        
        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player3.png"));
        
        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player4.png"));        
        
        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_up1.png"));
        
        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_up2.png"));
        
        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_up3.png"));
        
        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_up4.png"));
        
        
        
        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_left1.png"));        
        
        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_left2.png"));        
        
        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_left3.png"));
        
        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_left4.png"));
        
        
        
        
        
        
        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_right1.png"));
        
        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_right2.png"));
        
        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_right3.png"));
        
        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "player" + File.separator + "player_right4.png"));
        
        
        /*
        RED ENEMY IMAGES FOR ANIMATION FOR EACH CARDINAL DIRECTION
        */
        
        
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down1.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down2.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down3.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down4.png"));
        
        
        
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up1.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up2.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up3.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up4.png"));
        
        
        
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right1.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right2.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right3.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right4.png"));
        
        
        
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left1.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left2.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left3.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left4.png"));
        
              
        /*
        YELLOW ENEMY IMAGES FOR ANIMATION FOR EACH CARDINAL DIRECTION
        */
        
        
        
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down1.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down2.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down3.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down4.png"));
        
        
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up1.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up2.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up3.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up4.png"));
        
        
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right1.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right2.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right3.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right4.png"));
        
        
        
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left1.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left2.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left3.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left4.png"));
        
        
        //bomb gif
        
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb1.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb2.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb3.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb4.png"));
        
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb5.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb6.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb7.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb8.png"));
        //EXPLOSION SPRITES
        
        explosionC = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "centre.png");
        
        explosionEndT = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "end_top.png");
        explosionEndB = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "end_bottom.png");
        explosionEndR = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "end_right.png");
        explosionEndL = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "end_left.png");
        
        
        explosionH = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "horizontal.png");
        explosionV = loadImage(System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" + 
                File.separator + "resources" + File.separator + "explosion" + File.separator + "vertical.png");
    }
    
    public void draw(){
        
        
        //this method is used to draw content on the window
        //this method is called 60 times per second
        
        background(255,140,0);
        
        
        if(isWin){
            
            fill(0);
            textSize(20);
            textAlign(CENTER);
            text("You Win",240,240);
            
            return;
        }
        
        
        
        if(!isGameOver){
            
            if(lives <= 0 ){
                isGameOver = true;            
                return;
            }
        
        fill(0);
        textSize(20);
        text(lives,190,40);
        image(life,150,16);
        
        long elapsedTime = System.currentTimeMillis() - startTime;
        
        
        
        if(elapsedTime/1000!=0){
          
          textSize(20);
          
          if((180 - (int)(elapsedTime/1000)) > 0){
          
          text((180 - (int)(elapsedTime/1000)),305,40); 
          
          }else
              isGameOver = true;
              
        }else{
          textSize(20);
          text((180),305,40); 
        }
        
        
        
          image(clock,270,16);

          //loadLevel
          
          Level level = gameLevels[levelIndex];
          map = level.mapArray;
          
          
          //reset bombguy position when he dies
          if(canResetLevel){
              
                bombGuy = new BombGuy();
                
                canResetLevel = false;            
                
          }
          
          
          
          for(int i=0;i<13;i++){
              
              
              for(int j=0;j<15;j++){
                  
                   
                  
                  
                  switch(map[i][j]){
                      
                      case 'W':
                          
                          image(solidw,j*32,(i*32)+64);
                          
                          break;                                              
                      
                          
                          
                      
                      case 'H':
                          //horizontal explosion image
                          image(emptyw,j*32,(i*32)+64);
                          image(explosionH,j*32,(i*32)+64);
                          
                          break;
                          
                      case 'V':
                          //vertical explosion image
                          image(emptyw,j*32,(i*32)+64);
                          image(explosionV,j*32,(i*32)+64);
                          
                          
                          break;
                          
                          
                      case 'J':
                          
                          //end bottom explosion image
                          image(emptyw,j*32,(i*32)+64);
                          image(explosionEndB,j*32,(i*32)+64);
                          
                          break;
                          
                      case 'K':
                          //end top explosion image
                          image(emptyw,j*32,(i*32)+64);
                          image(explosionEndT,j*32,(i*32)+64);
                          
                          break;
                          
                          case 'L':
                              
                              //end left explosion image
                              image(emptyw,j*32,(i*32)+64);
                              image(explosionEndL,j*32,(i*32)+64);
                              
                          break;
                          
                          case 'M':
                              
                              //end right explosion image
                              image(emptyw,j*32,(i*32)+64);
                              image(explosionEndR,j*32,(i*32)+64);
                              
                              break;
                              
                          case 'C':
                              
                              //center explosion image
                              image(emptyw,j*32,(i*32)+64);
                              image(explosionC,j*32,(i*32)+64);
                              
                              
                              break;
                          
                      case 'P':

                          isPlayerInMap = true;
                         
                          //this condition is for changing level
                          if(i == goalIndex[0] && j == goalIndex[1]){
                              
                              if(levelIndex == gameLevels.length-1)
                                  isWin = true;
                              else
                                  levelIndex++;
                              
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
                          
                          
                          //place bomb if space is pressed by the player
                           if(bomb.isPlaced)     {
                              
                              bomb.isExploded = false;
                              bomb.bombX = j;
                              bomb.bombY = i;
//                              image(bombGif,j*32,(i*32)+64);
                              bombAnimation = new Animation(0.25f);
                         
                              
                              
                              new Timer().schedule(new TimerTask(){
                              
                                  
                                  @Override
                                  public void run(){
                                      
                                     
                                      bomb.canExplode = true;
                                      
                                      
                                      
                                  }
                              
                              }, 2000);
                              bomb.isPlaced = false;
                              
                          }
                                                    
                           
                           
                          int yPlayerPos = (abs(i-1) * 32)+ start_y_pos_player;
                                                                 
                          
                          positionAnimation(bombGuy.anim_direction,j*32,yPlayerPos,playerDownFramesList,playerUpFramesList,playerRightFramesList,
                                  playerLeftFramesList);
                            
                            
                          break;
                      case ' ':   
                          
                          image(emptyw,j*32,(i*32)+64);   

                           //explosion code here
                           
                            if(bomb.bombX == j && bomb.bombY == i)
                                    image(bombAnimation.play(bombFramesList),bomb.bombX*32,(bomb.bombY*32)+64);
                              
                                    if(bomb.canExplode){
                                        bomb.explode(bomb.bombX, bomb.bombY, map);
                                        bomb.canExplode = false;
                                  
                                    new Timer().schedule(new TimerTask(){
                                      
                                          @Override
                                          public void run(){
                                          
                                            //this code will run after explosion
                                            bomb.isExploded = true;
                                            if(bomb.explodeRange.size() > 0){
                                              
                                             
                                              
                                                  map[bomb.bombY][bomb.bombX] = ' ';
                                                  
                                              
                                              
                                              
                                          }
                                              
                                          
                                          for(int l=0;l<bomb.explodeRange.size();l++){
                                              
                                              int[][] rangeDirection = bomb.explodeRange.get(l);
                                              
                                              
                                              if(rangeDirection[0][0] != -1 && rangeDirection[0][1] != -1)
                                                  if(rangeDirection[0][0] == goalIndex[0] && rangeDirection[0][1] == goalIndex[1])
                                                      map[rangeDirection[0][0]][rangeDirection[0][1]] = 'G';
                                                  else                                                      
                                                      map[rangeDirection[0][0]][rangeDirection[0][1]] = ' ';
                                              
                                              
                                              if(rangeDirection[1][0] != -1 && rangeDirection[1][1] != -1)
                                                  if(rangeDirection[1][0] == goalIndex[0]  && rangeDirection[1][1] == goalIndex[1])
                                                      map[rangeDirection[1][0]][rangeDirection[1][1]] = 'G';
                                                  else
                                                      map[rangeDirection[1][0]][rangeDirection[1][1]] = ' ';
                                              
                                              
                                              
                                          }
                                          
                                          //this will remove the bomb from map
                                                bomb.bombX = -1;
                                                bomb.bombY = -1;
                                          
                                      }
                                      
                                      
                                  }, 500);
                                  
                                  
                              }
          
                          
                          break;
                          
                      case 'R':
                          
                          
                         
                               redEnemyAI(i,j,map);
                        
                          
                          break;
                      case 'Y':
                          
                                       
                          
                                yellowEnemyAI(i,j,map);
                          
                          
                          
                          break;
                          
                      case 'X':
                          
                          
                          yellowEnemyAI(i,j,map);
                          
                          map[i][j] = 'X';
                          
                          redEnemyAI(i,j,map);
                          
                          
                          
                          
                          
                          
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
          
          if(isPlayerInMap){
                  //we will reset the value of isPlayerInMap to check every time the existence of player in map
                  isPlayerInMap = false;
              
              }else{
                  
                  //if player is not present in map (because of explosion it maynot shown in map)
                  //then we add the player respawn position in level according to its level
                  
                  if(bomb.isExploded){
                      
                      //here checking if any bomb is placed and exploded then show the player in the map
                      //this variable will be if the bomb is not placed
                      // in this way this piece of will be called in other cases Like when player
                      //comes in contact with any enemy
                  lives--;
                      
                  Integer[] playerLevelStartPos = pStartPosLevelList.get(levelIndex);
                  
                  map[playerLevelStartPos[0]][playerLevelStartPos[1]] = 'P';
                  
                  
                  
                  isPlayerInMap = true;
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
            
            File config = new File(System.getProperty("user.dir") + File.separator +"config.json");
            Scanner scanner = new Scanner(config);
            
            while(scanner.hasNextLine())
                data += scanner.nextLine();
            

            
           
            scanner.close();
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return data;
    }
    
    private static Level[] configureLevels(String jsonData){
        
        Level[] levelArray = null;
        
        try{
            
        JSONObject obj = JSONObject.parse(jsonData);      
        JSONArray levels = obj.getJSONArray("levels");
        lives = obj.getInt("lives");
        
        
        
        levelArray = new Level[levels.size()];
        for(int i=0;i<levels.size();i++){
            
            JSONObject levelObj = levels.getJSONObject(i);
            Level level = new Level(levelObj.getString("path"),levelObj.getInt("time"));
            
            level.setMap(readMapFromFile(level.path));
            
            levelArray[i] = level;
            
            
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
                      
                      if(row.charAt(j) == 'P'){
                          
                          Integer[] playerStartPos = new Integer[2];
                          
                          playerStartPos[0] = i;
                          playerStartPos[1] = j;
                          
                          
                          pStartPosLevelList.add(playerStartPos);
                          
                      }
                      
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
    
    
    
    
   
    
    
    @Override
    public void keyReleased(){
        
        int newdire = -1;
        if(keyCode == DOWN)
            newdire = 0;
        else if(keyCode == UP)
            newdire = 1;
        else if(keyCode == RIGHT)
            newdire = 2;
        else if(keyCode == LEFT)
            newdire = 3;
        else if(keyCode == 32){ //keyCode 32 is for spacebar
            
            if(bomb.isExploded){
            bomb.isPlaced = true;
            bomb.isExploded = false;
            bomb.placeTime = System.currentTimeMillis();
            }
            
        }
        
        if(newdire != -1)
            bombGuy.direction = bombGuy.anim_direction = newdire;
        
        
        
    }
    
    private void positionAnimation(int mDirection,int xPos,int yPos,ArrayList<PImage> downFramesList,ArrayList<PImage> upFramesList,
                                ArrayList<PImage> rightFramesList,ArrayList<PImage> leftFramesList){
        
        
        switch (mDirection) {
                          
                          case -1:
                          case 0:
                              
                              image(characterAnimation.play(downFramesList),xPos,yPos);
                              
                              break;
                          case 1:
                              
                              image(characterAnimation.play(upFramesList),xPos,yPos);
                              
                              break; 
                          case 2:
                              
                              image(characterAnimation.play(rightFramesList),xPos,yPos);
                              
                              break;
                          case 3:
                              
                              image(characterAnimation.play(leftFramesList),xPos,yPos);
                              
                              break;
                          default:
                              break;
                      
                      }
        
        
    }
    
    
    
    private void redEnemyAI(int i,int j,char[][] map){
        
        
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
                      
                          
                          
                          
                      positionAnimation(redEnemy.redAnimDirection,j*32,yRedPos,redEnDownFramesList,redEnUpFramesList,redEnRightFramesList,redEnLeftFramesList);
        
        
    }
    
    
    private void yellowEnemyAI(int i,int j, char[][] map){
        
        
        
                          
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
                             positionAnimation(yellEnemy.yellowAnimDirection,j*32,yYellowPos,yellowEnDownFramesList,yellowEnUpFramesList,yellowEnRightFramesList,yellowEnLeftFramesList);
                                  
                               
                                 
        
    }
    
    
    
    
}
