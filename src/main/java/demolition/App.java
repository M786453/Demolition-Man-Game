/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demolition;



import processing.core.*;
import java.util.ArrayList;





/**
 * Represent the main interface of Game
 * @author Ahtesham Sarwar
 */
public class App extends PApplet{

    /**
     * Represents the Height of window to be displayed
     */
    public static final int HEIGHT = 480;
    
    /**
     * Represents the width of window to be displayed
     */
    public static final int WIDTH = 480;
    
    
    /**
     * Represents the FPS(frames per second) of Game
     */
    private final int FPS = 60;
    
    
    /**
     * Represents BombGuy Object of Game
     */
    public static BombGuy bombGuy;
    
    /**
     * Represents RedEnemy Object of Game
     */
    public static RedEnemy redEnemy;
    
    /**
     * Represents YellowEnemy Object of Game
     */
    public static YellowEnemy yellEnemy;

    
    /**
     * Represents Bomb Object of Game
     */
    public static Bomb bomb;
    
    /**
     * Tells whether Game is over or not
     */
    private boolean isGameOver = false;
    
    /**
     * Tells whether player won or not
     */
    public static boolean isWin = false;
    
    
    /**
     * Represents the time when game starts
     */
    public static long startTime;
    
    /**
     * Represents the list levels in Game
     */
    public static ArrayList<Level> gameLevels;
    
    
    /**
     * Represents the index of level of game
     */
    public static int levelIndex = 0;   
    
    
    /**
     * Represents the map of current level of game
     */
    public static char[][] map;    
    
    
    /**
     * Represents the x-axis directions 
     */            
    public static final int[] x_direction = {0,0,1,-1};
    
    /**
     * Represents y-axis directions
     */
    public static final int[] y_direction = {1,-1,0,0};        
    
    /**
     * Represents Position of Goal Tile in current level
     */
    public static final int[] goalIndex = new int[2];    
    
    
    /**
     * Represents the Animation Object for game Objects (BombGuy,RedEnemy,YellowEnemy)
     */
    private Animation characterAnimation;
    
    /**
     * Represents the Animation Object for Bomb Animation
     */
    public static Animation bombAnimation;
    
    /**
     * Represents the FramesLoader Object used to load frames into game
     */
    public FramesLoader framesLoader = new FramesLoader(this);
    
    
    /**
     * @param args the command line arguments
     * we extend this class from PApplet 
     * so that we can use powerful animations of processing
     * library
     */
    public static void main(String[] args) {
      
        PApplet.main("demolition.App");

        
    }
    
    
    
    @Override
    public void settings(){
        
        //set the size of window here
        this.size(HEIGHT,WIDTH);
        
    }
    
    
    
    @Override
    public void setup(){
        
        /*
        Here we need to instantiate every object which we draw on the window
        */
        
        
        
        frameRate(FPS);
             
        startTime = System.currentTimeMillis();   
        
        bombGuy = new BombGuy(); 
        bomb = new Bomb();                       
        redEnemy = new RedEnemy();        
        yellEnemy = new YellowEnemy();
        
        String configData = Configurations.readConfig();
        gameLevels = Configurations.configureLevels(configData);
       
         
        
        characterAnimation = new Animation(this,0.2f);
        bombAnimation = new Animation(this,bomb.PerFrameTime);

        
        
        /*
        TILES AND ICONS INITIALIZATION
        */
        
        
        framesLoader.loadTiles();        
        framesLoader.loadIcons();

        
        /*
        PLAYER FRAMES LISTS INITIALIZATION
        */
       
        framesLoader.loadPlayerFramesInLists();

        /*
        RED ENEMY FRAMES LISTS INITIALIZATION
         */
        
        framesLoader.loadRedEnemyFramesInLists();
        

        /*
        YELLOW ENEMY FRAMES LISTS INITIALIZATION
         */
        
        
        framesLoader.loadYellowEnemyFramesInLists();
        
        
        
        /*
         BOMB FRAMES LISTS INITIALIZATION
         */
        
        framesLoader.loadBombFrames();
       
        /*
         EXPLOSION FRAMES LISTS INITIALIZATION
         */

        framesLoader.loadExplosionFrames();
        
        
    }
    
    
    @Override
    public void draw(){
        
      startGame();  
      
    }
    
    
    
    @Override
    public void keyReleased(){
        
        int newdire = -1;
        switch (keyCode) {
            case DOWN:
                newdire = 0;
                break;
            case UP:
                newdire = 1;
                break;
            case RIGHT:
                newdire = 2;
                break;
            case LEFT:
                newdire = 3;
                break;
            case 32:
                //keyCode 32 is for spacebar

                if(Bomb.isExploded){
                    bomb.isPlaced = true;
                    Bomb.isExploded = false;
                    bomb.placeTime = System.currentTimeMillis();
                }
                
                break;
            default:
                break;
        }
        
        if(newdire != -1)
            bombGuy.direction = bombGuy.anim_direction = newdire;
        
        
        
    }
    
    
    /**
     * Control and Draw Red Enemy in Game Window
     * @param i Represents Y-axis Position of Red Enemy
     * @param j Represents X-axis Position of Red Enemy
     * @param map Represents map of current of level
     */
    private void redEnemyAI(int i,int j,char[][] map){
        
        redEnemy.controller(i, j);
       
        image(framesLoader.emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animating red enemy in right direction
        int yRedPos = (abs(i - 1) * 32) + RedEnemy.RED_OFFSET;

        characterAnimation.positionAnimation(redEnemy.redAnimDirection, j * 32, yRedPos, framesLoader.redEnDownFramesList, framesLoader.redEnUpFramesList, 
                framesLoader.redEnRightFramesList, framesLoader.redEnLeftFramesList);

        
    }
    
    
    
    /**
     * Control and draw Yellow Enemy in Game Window
     * @param i Represents Y-axis Position of Yellow Enemy
     * @param j Represents X-axis Position of Yellow Enemy
     * @param map Represents map of current of level
     */
    private void yellowEnemyAI(int i,int j, char[][] map){
        
        yellEnemy.controller(i, j);

        image(framesLoader.emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animated yellow in left direction
        int yYellowPos = ((i - 1) * 32) + YellowEnemy.YELLOW_OFFSET;

        //here only for direction left (test) -> not completed
        characterAnimation.positionAnimation(yellEnemy.yellowAnimDirection, j * 32, yYellowPos, framesLoader.yellowEnDownFramesList, 
                framesLoader.yellowEnUpFramesList, framesLoader.yellowEnRightFramesList, 
                framesLoader.yellowEnLeftFramesList);


    }
    
    
    /**
     * Use to show Win Screen
     */
    private void showWinScreen(){
        
            
            fill(0);
            textSize(20);
            textAlign(CENTER);
            text("You Win",240,240);
           
    }
    
    
    /**
     * Use to show Game Over Screen
     */
    private void showGameOverScreen(){
        
        
         
        fill(0);
        textSize(20);
        textAlign(CENTER);
        text("GAME OVER",240,240);
        
        
    }
    
    
    
    /**
     * Use to show Lives of Bomb Guy on Game window
     */
    private void showLives(){
        
        
        fill(0);
        textSize(20);
        text(BombGuy.lives,190,40);
        image(framesLoader.life,150,16);
        
        
    }
    
    
    /**
     * Use to represent Clock and Time on Game window
     * @param level Represent the current level of game
     */
    private void showClockWithTime(Level level){
        
        
        long elapsedTime = System.currentTimeMillis() - startTime;
      
        if(elapsedTime/1000!=0){
          
          textSize(20);
          
          if((level.time - (int)(elapsedTime/1000)) > 0){
          
          text((level.time - (int)(elapsedTime/1000)),305,40); 
          
          }else
              isGameOver = true;
              
        }else{
          textSize(20);
          text((level.time),305,40); 
        }
        
          image(framesLoader.clock,270,16);
        
        
    }
    
    /**
     * Use to start the Game
     */
    private void startGame(){
        
          
        /*
        This method is used to draw content on the window.
        This method is called 60 times per second
        */
        background(255,140,0);
        
        if(isWin){
            
            showWinScreen();
            
            return;
        }
        
        
        
        if(!isGameOver){
            
            /*
                IF BOMB GUY LIVES ARE LESS THAN EQUAL TO 0, GAME WILL BE OVER
            */
            
            if(BombGuy.lives <= 0 ){
                isGameOver = true;            
                return;
                
            }
        
        
        
        //loadLevel
        
        if(gameLevels.isEmpty()){
            isGameOver = true;
            return;
        }
        
        Level level = gameLevels.get(levelIndex);
        map = level.mapArray;
        
        
        showLives();
        
        
        showClockWithTime(level);
        

          
          for(int i=0;i<Map.ROWS;i++){
              
              
              for(int j=0;j<Map.COLUMNS;j++){
                  
                   
                  
                  
                  switch(map[i][j]){
                      
                      case 'W':
                          
                          image(framesLoader.solidw,j*32,(i*32)+ Map.OFFSET);
                          
                          break;                                              
                      
                          
                          
                      
                      case 'H':
                          //horizontal explosion image
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);
                          image(framesLoader.explosionH,j*32,(i*32)+ Map.OFFSET);
                          
                          break;
                          
                      case 'V':
                          //vertical explosion image
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);
                          image(framesLoader.explosionV,j*32,(i*32)+ Map.OFFSET);
                          
                          
                          break;                          
                          
                          case 'C':
                              
                              //center explosion image
                              image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);
                              image(framesLoader.explosionC,j*32,(i*32)+ Map.OFFSET);
                              
                              
                              break;
                          
                      case 'P':

                          bombGuy.isPlayerInMap = true;
                         
                          bombGuy.changeLevelIfPlayerAtGoal(i,j);
                          
                          bombGuy.controlPlayer(i,j);                          
                          
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);
                          
                          
                          //place bomb if space is pressed by the player
                           if(bomb.isPlaced)     {
                              
                              bomb.placeBomb(i,j);
                              
                          }
                                                    
                           
                          int yPlayerPos = (abs(i-1) * 32)+ bombGuy.PLAYER_OFFSET;
                          
                          characterAnimation.positionAnimation(bombGuy.anim_direction,j*32,yPlayerPos,framesLoader.playerDownFramesList,framesLoader.playerUpFramesList,framesLoader.playerRightFramesList,
                                  framesLoader.playerLeftFramesList);
                            
                            
                          break;
                      case ' ':   
                          
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);   

                           
                            /*
                                If bomb is present at i,j position then show the bomb Animation
                            */
                           
                            bombAnimation.positionBombAnimation(i,j,framesLoader.bombFramesList);
                              
                               
                            bomb.removeExplosionEffectAfterExplosion();
          
                          
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
                          
                          image(framesLoader.brokenw,j*32,(i*32)+ Map.OFFSET);
                          
                          break;
                      case 'G':
                          
                          
                          
                          goalIndex[0] = i;
                          goalIndex[1] = j;
                          image(framesLoader.goalw,j*32,(i*32)+ Map.OFFSET);
                        
                          break;
                   
                      
                  }
                  
              }
          
    }
          
            if (bombGuy.isPlayerInMap) {

                //we will reset the value of isPlayerInMap to check every time the existence of player in map
                bombGuy.isPlayerInMap = false;

            } else {

                /*
                  if player is not present in map (mean player is died)
                  then we add the player respawn position in level according to its level
                 */
                bombGuy.playerDieAndResetLevel();

            }
          
    }else{
       
        showGameOverScreen();
            
    }
            
      
        
    }
   
    
}
