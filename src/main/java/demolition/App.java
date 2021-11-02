/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demolition;



import processing.core.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;




/**
 * @author Ahtesham Sarwar
 */
public class App extends PApplet{

    public static final int HEIGHT = 480;
    public static final int WIDTH = 480;
    private final int FPS = 60;
    private static boolean isPlayerInMap;
    private BombGuy bombGuy;
    private RedEnemy redEnemy;
    private YellowEnemy yellEnemy;    
    public static Bomb bomb;
    private boolean isGameOver;
    private boolean isWin;
    
    public static long startTime;
    private ArrayList<Level> gameLevels;
    private int levelIndex = 0;   
    private char[][] map;    
    private int redFrameCounter2 = 1;
    private int yellowFrameCounter = 1;    
    private final int[] x_direction = {0,0,1,-1};
    private final int[] y_direction = {1,-1,0,0};        
    public final static int[] goalIndex = new int[2];    
    private final int start_y_pos_player = 80;
    private final int start_y_pos_red_en = 80;
    private final int start_y_pos_yell_en = 80;    
    private Animation characterAnimation;
    private Animation bombAnimation;
    private FramesLoader framesLoader = new FramesLoader(this);
    
    
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
        
        isPlayerInMap = false;
             
        startTime = System.currentTimeMillis();   
        
        bombGuy = new BombGuy(); 
        bomb = new Bomb();                       
        redEnemy = new RedEnemy();        
        yellEnemy = new YellowEnemy();
        
        String configData = Configurations.readConfig();
        gameLevels = Configurations.configureLevels(configData);
       
         
        
        characterAnimation = new Animation(0.2f);
        bombAnimation = new Animation(bomb.PerFrameTime);

        isWin = false;       
        isGameOver = false;
        
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
            if (redFrameCounter2 == 60) {

            if (redEnemy.redEnDirection == 0 || redEnemy.redEnDirection == 1) {

                //down or up
                int yDirec = y_direction[redEnemy.redEnDirection];

                map = redEnemy.movement(i, j, yDirec, map);

            } else if (redEnemy.redEnDirection == 2 || redEnemy.redEnDirection == 3) {
                //right or left

                int xDirec = x_direction[redEnemy.redEnDirection];

                map = redEnemy.movement(i, j, xDirec, map);

            }

            redFrameCounter2 = 1;

        } else {

            redFrameCounter2++;

        }

        image(framesLoader.emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animating red enemy in right direction
        int yRedPos = (abs(i - 1) * 32) + start_y_pos_red_en;

        positionAnimation(redEnemy.redAnimDirection, j * 32, yRedPos, framesLoader.redEnDownFramesList, framesLoader.redEnUpFramesList, 
                framesLoader.redEnRightFramesList, framesLoader.redEnLeftFramesList);

        
    }
    
    
    private void yellowEnemyAI(int i,int j, char[][] map){
        
            if (yellowFrameCounter == 60) {

            if (yellEnemy.yellowEnDirection == 0 || yellEnemy.yellowEnDirection == 1) {

                //down or up
                int yDirec = y_direction[yellEnemy.yellowEnDirection];

                map = yellEnemy.movement(i, j, yDirec, map);

            } else if (yellEnemy.yellowEnDirection == 2 || yellEnemy.yellowEnDirection == 3) {

                //right or left
                int xDirec = x_direction[yellEnemy.yellowEnDirection];

                map = yellEnemy.movement(i, j, xDirec, map);

            }

            yellowFrameCounter = 1;

        } else {

            yellowFrameCounter++;

        }

        image(framesLoader.emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animated yellow in left direction
        int yYellowPos = ((i - 1) * 32) + start_y_pos_yell_en;

        //here only for direction left (test) -> not completed
        positionAnimation(yellEnemy.yellowAnimDirection, j * 32, yYellowPos, framesLoader.yellowEnDownFramesList, 
                framesLoader.yellowEnUpFramesList, framesLoader.yellowEnRightFramesList, 
                framesLoader.yellowEnLeftFramesList);


    }
    
    
    
    
    
    
    
   
    
    
    private void showWinScreen(){
        
            /*
            SHOW WIN SCREEN
            */
            
            fill(0);
            textSize(20);
            textAlign(CENTER);
            text("You Win",240,240);
           
    }
    
    
    private void showGameOverScreen(){
        
         /*
            GAME OVER SCREEN
            */
         
        fill(0);
        textSize(20);
        textAlign(CENTER);
        text("GAME OVER",240,240);
        
        
    }
    
    
    private void playerDieAndResetLevel(){
        
        
        if(bomb.isExploded){
                      
                      /*
                      Here checking if any bomb is placed and exploded then show the player in the map
                      This variable will be if the bomb is not placed
                      In this way this piece of will be called in other cases Like when player
                      comes in contact with any enemy
                      
                      NOTE: Here the bomb.isExploded is only false when bomb is placed and it will be true
                      after explosion or even when bomb is not placed. So player will lose life and level will reset when in
                      contact with explosion or an enemy.
                      */
                      
                      
                  
                      
                  gameLevels.get(levelIndex).resetLevel();
                      
                  Integer[] playerLevelStartPos = BombGuy.pStartPlayerPosLevelList.get(levelIndex);
                  
                  map[playerLevelStartPos[0]][playerLevelStartPos[1]] = 'P';
                  
                  
                  
                  bombGuy = new BombGuy();
                  BombGuy.lives--;
                  
                  redEnemy = new RedEnemy();
                  yellEnemy = new YellowEnemy();
                  
                  isPlayerInMap = true;
                  
                  }
        
        
    }
    
    
    private void positionBombAnimation(int i, int j){
        
        if(Bomb.bombX == j && Bomb.bombY == i)
                                    image(bombAnimation.play(framesLoader.bombFramesList),Bomb.bombX*32,(Bomb.bombY*32) + Map.OFFSET);
        
        
    }
    
    
    
    
    private void removeExplosionEffectAfterExplosion(){
        
        
             if(bomb.canExplode){
                                        bomb.explode(Bomb.bombX, Bomb.bombY, map);
                                        bomb.canExplode = false;
                                  
                                    new Timer().schedule(new TimerTask(){
                                      
                                          @Override
                                          public void run(){
                                          
                                            //this code will run after explosion
                                            bomb.isExploded = true;
                                            if(bomb.explodeRange.size() > 0){
                                              
                                             
                                              
                                                  map[Bomb.bombY][Bomb.bombX] = ' ';
                                                  
                                              
                                              
                                              
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
                                                Bomb.bombX = -1;
                                                Bomb.bombY = -1;
                                          
                                      }
                                      
                                      
                                  }, bomb.ExplosionTime);
                                  
                                  
                              }
        
        
    }
    
    
    private void placeBomb(int i, int j){
        
        
        bomb.isExploded = false;
                              Bomb.bombX = j;
                              Bomb.bombY = i;

                              bombAnimation = new Animation(bomb.PerFrameTime);
                         
                              
                              
                              new Timer().schedule(new TimerTask(){
                              
                                  
                                  @Override
                                  public void run(){
                                      
                                     
                                      bomb.canExplode = true;
                                      
                                      
                                      
                                  }
                              
                              }, bomb.BombTime);
                              bomb.isPlaced = false;
        
        
    }
    
    
    private void showLives(){
        
        
        fill(0);
        textSize(20);
        text(BombGuy.lives,190,40);
        image(framesLoader.life,150,16);
        
        
    }
    
    
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
    
    
    
    
    
    
    
    
    private void changeLevelIfPlayerAtGoal(int i, int j){
        
        /*
            If Player at Goal Position, change the level. If Player
            Goal Tile of last level , then player will win game
        */
        
                          if(i == goalIndex[0] && j == goalIndex[1]){
                              
                              if(levelIndex == gameLevels.size()-1)
                                  isWin = true;
                              else
                                  levelIndex++;
                              
                              startTime = System.currentTimeMillis();
                              
                              bombGuy.anim_direction = -1;
                          }
        
        
    }
    
    
    
    private void controlPlayer(int i, int j){
        
        
        //this will change the position of player if there is empty or goal tile towards the position
        
                          //requested by user
                          if(bombGuy.direction == 0 || bombGuy.direction == 1){
                              //move down or up
                              
                              
                              int yDirec = y_direction[bombGuy.direction]; 
                              
                              map = bombGuy.movement(i, j, yDirec , map);
                              
                              
                              
                              
                          }else if(bombGuy.direction == 2 || bombGuy.direction == 3){
                              //move right or left
                              
                              int xDirec = x_direction[bombGuy.direction]; 
                              
                              map = bombGuy.movement(i, j, xDirec, map);
                              
                            
                          }
        
        
    }
    
    
    
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
        
        if(gameLevels.size() == 0){
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

                          isPlayerInMap = true;
                         
                          
                          
                          changeLevelIfPlayerAtGoal(i,j);
                          
                          
                          controlPlayer(i,j);                          
                          
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);
                          
                          
                          //place bomb if space is pressed by the player
                           if(bomb.isPlaced)     {
                              
                              placeBomb(i,j);
                              
                          }
                                                    
                           
                          int yPlayerPos = (abs(i-1) * 32)+ start_y_pos_player;
                          
                          positionAnimation(bombGuy.anim_direction,j*32,yPlayerPos,framesLoader.playerDownFramesList,framesLoader.playerUpFramesList,framesLoader.playerRightFramesList,
                                  framesLoader.playerLeftFramesList);
                            
                            
                          break;
                      case ' ':   
                          
                          image(framesLoader.emptyw,j*32,(i*32)+ Map.OFFSET);   

                           
                            /*
        
                                If bomb is present at i,j position then show the bomb Animation
        
                            */
                           
                            positionBombAnimation(i,j);
                              
                               
                            removeExplosionEffectAfterExplosion();
          
                          
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
          
          if(isPlayerInMap){
                  
                  //we will reset the value of isPlayerInMap to check every time the existence of player in map
                  
                  isPlayerInMap = false;
              
              }else{
                  
                  /*
                  if player is not present in map (mean player is died)
                  then we add the player respawn position in level according to its level
                  */
                  
                  playerDieAndResetLevel();
                  
                  
              }
          
    }else{
       
        showGameOverScreen();
            
    }
            
      
        
    }
   
    
}
