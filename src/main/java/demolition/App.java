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
    private PImage solidw,brokenw,emptyw,goalw,life,clock,explosionC,explosionH,explosionV;
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
    
    private static ArrayList<Integer[]> pStartPlayerPosLevelList;
       
    private ArrayList<PImage> playerDownFramesList;
    private ArrayList<PImage> playerUpFramesList;
    private ArrayList<PImage> playerLeftFramesList;
    private ArrayList<PImage> playerRightFramesList;
    
    
    private ArrayList<PImage> redEnDownFramesList;
    private ArrayList<PImage> redEnUpFramesList;
    private ArrayList<PImage> redEnLeftFramesList;
    private ArrayList<PImage> redEnRightFramesList;
    
    
    private ArrayList<PImage> yellowEnDownFramesList;
    private ArrayList<PImage> yellowEnUpFramesList;
    private ArrayList<PImage> yellowEnLeftFramesList;
    private ArrayList<PImage> yellowEnRightFramesList;
    
    private ArrayList<PImage> bombFramesList;

    
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
        pStartPlayerPosLevelList = new ArrayList<>();        
        startTime = System.currentTimeMillis();   
        
        bombGuy = new BombGuy(); 
        bomb = new Bomb();                       
        redEnemy = new RedEnemy();        
        yellEnemy = new YellowEnemy();
        
        String configData = readConfig();
        gameLevels = configureLevels(configData);
        
        bombFramesList = new ArrayList<>();
        characterAnimation = new Animation(0.2f);
        bombAnimation = new Animation(bomb.PerFrameTime);

        isWin = false;       
        isGameOver = false;
        
        /*
        TILES AND ICONS INITIALIZATION
        */
        
        
        loadTiles();        
        loadIcons();

        
        /*
        PLAYER FRAMES LISTS INITIALIZATION
        */
       
        loadPlayerFramesInLists();

        /*
        RED ENEMY FRAMES LISTS INITIALIZATION
         */
        
        loadRedEnemyFramesInLists();
        

        /*
        YELLOW ENEMY FRAMES LISTS INITIALIZATION
         */
        
        
        loadYellowEnemyFramesInLists();
        
        
        
        /*
         BOMB FRAMES LISTS INITIALIZATION
         */
        
        loadBombFrames();
       
        /*
         EXPLOSION FRAMES LISTS INITIALIZATION
         */

        loadExplosionFrames();
        
        
    }
    
    
    @Override
    public void draw(){
        
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
            
            if(bombGuy.getLives() <= 0 ){
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
                          
                          image(solidw,j*32,(i*32)+ Map.OFFSET);
                          
                          break;                                              
                      
                          
                          
                      
                      case 'H':
                          //horizontal explosion image
                          image(emptyw,j*32,(i*32)+ Map.OFFSET);
                          image(explosionH,j*32,(i*32)+ Map.OFFSET);
                          
                          break;
                          
                      case 'V':
                          //vertical explosion image
                          image(emptyw,j*32,(i*32)+ Map.OFFSET);
                          image(explosionV,j*32,(i*32)+ Map.OFFSET);
                          
                          
                          break;                          
                          
                          case 'C':
                              
                              //center explosion image
                              image(emptyw,j*32,(i*32)+ Map.OFFSET);
                              image(explosionC,j*32,(i*32)+ Map.OFFSET);
                              
                              
                              break;
                          
                      case 'P':

                          isPlayerInMap = true;
                         
                          
                          
                          changeLevelIfPlayerAtGoal(i,j);
                          
                          
                          controlPlayer(i,j);                          
                          
                          image(emptyw,j*32,(i*32)+ Map.OFFSET);
                          
                          
                          //place bomb if space is pressed by the player
                           if(bomb.isPlaced)     {
                              
                              placeBomb(i,j);
                              
                          }
                                                    
                           
                          int yPlayerPos = (abs(i-1) * 32)+ start_y_pos_player;
                          
                          positionAnimation(bombGuy.anim_direction,j*32,yPlayerPos,playerDownFramesList,playerUpFramesList,playerRightFramesList,
                                  playerLeftFramesList);
                            
                            
                          break;
                      case ' ':   
                          
                          image(emptyw,j*32,(i*32)+ Map.OFFSET);   

                           
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
                          
                          image(brokenw,j*32,(i*32)+ Map.OFFSET);
                          
                          break;
                      case 'G':
                          
                          
                          
                          goalIndex[0] = i;
                          goalIndex[1] = j;
                          image(goalw,j*32,(i*32)+ Map.OFFSET);
                        
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
    
    
    private String readConfig(){
        
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
    
    
    
    private ArrayList<Level> configureLevels(String jsonData){
        
        ArrayList<Level> levelList = new ArrayList<>();
        
        try{
            
        JSONObject obj = JSONObject.parse(jsonData);      
        JSONArray levels = obj.getJSONArray("levels");
        bombGuy.setLives(obj.getInt("lives")); 
       
        
        for(int i=0;i<levels.size();i++){
            
            JSONObject levelObj = levels.getJSONObject(i);
            Level level = new Level(levelObj.getString("path"),levelObj.getInt("time"));
            
            char[][] level_map = readMapFromFile(level.path);
            
            level.setMap(level_map);
            
            
            if(Map.validateMap(level_map))
            levelList.add(level);
            
            
        }
        
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return levelList;
        
    }
    
    private char[][] readMapFromFile(String path){
        
        char[][] map = new char[Map.ROWS][Map.COLUMNS];
        
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
                          
                          
                          pStartPlayerPosLevelList.add(playerStartPos);
                          
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

        image(emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animating red enemy in right direction
        int yRedPos = (abs(i - 1) * 32) + start_y_pos_red_en;

        positionAnimation(redEnemy.redAnimDirection, j * 32, yRedPos, redEnDownFramesList, redEnUpFramesList, redEnRightFramesList, redEnLeftFramesList);

        
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

        image(emptyw, j * 32, (i * 32) + Map.OFFSET);

        //animated yellow in left direction
        int yYellowPos = ((i - 1) * 32) + start_y_pos_yell_en;

        //here only for direction left (test) -> not completed
        positionAnimation(yellEnemy.yellowAnimDirection, j * 32, yYellowPos, yellowEnDownFramesList, yellowEnUpFramesList, yellowEnRightFramesList, yellowEnLeftFramesList);


    }
    
    
    
    
    private void loadPlayerDownFramesList(){
        
        playerDownFramesList = new ArrayList<>();
        
        
        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player1.png"));

        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player2.png"));

        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player3.png"));

        playerDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player4.png"));

        
        
        
    }
    
    
    private void loadPlayerRightFramesList(){
        
        playerRightFramesList = new ArrayList<>();
        
        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right1.png"));

        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right2.png"));

        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right3.png"));

        playerRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right4.png"));
        
        
        
    }
    
    
    private void loadPlayerLeftFramesList(){
        
        playerLeftFramesList = new ArrayList<>();        
        
        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left1.png"));

        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left2.png"));

        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left3.png"));

        playerLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left4.png"));

        
        
    }
    
    
    
    private void loadPlayerUpFramesList(){
        
        
        playerUpFramesList = new ArrayList<>();
        
        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up1.png"));

        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up2.png"));

        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up3.png"));

        playerUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up4.png"));

        
        
        
    }
    
    
    private void loadRedEnemyDownFrames(){
        
        
        redEnDownFramesList = new ArrayList<>();
        
        
         redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down1.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down2.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down3.png"));
        redEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down4.png"));

        
    }
    
    
    private void loadRedEnemyUpFrames(){
        
        
        redEnUpFramesList = new ArrayList<>();
        
        
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up1.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up2.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up3.png"));
        redEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up4.png"));
        
    }
    
    private void loadRedEnemyRightFrames(){
        
        
        redEnRightFramesList = new ArrayList<>();
        
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right1.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right2.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right3.png"));
        redEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right4.png"));
        
        
    }
    
    private void loadRedEnemyLeftFrames(){
        
        redEnLeftFramesList = new ArrayList<>();
        
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left1.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left2.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left3.png"));
        redEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left4.png"));
        
    }
    
    
    private void loadYellowEnemyDownFrames(){
        
        
        yellowEnDownFramesList = new ArrayList<>();
        
        
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down1.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down2.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down3.png"));
        yellowEnDownFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down4.png"));

      
    }
    
    
    private void loadYellowEnemyUpFrames(){
        
        yellowEnUpFramesList = new ArrayList<>();
        
        
        
         yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up1.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up2.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up3.png"));
        yellowEnUpFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up4.png"));
        
        
    }
    
    
    private void loadYellowEnemyRightFrames(){
        
        
        yellowEnRightFramesList = new ArrayList<>();
        
         yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right1.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right2.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right3.png"));
        yellowEnRightFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right4.png"));
        
        
    }
    
    
    private void loadYellowEnemyLeftFrames(){
        
        yellowEnLeftFramesList = new ArrayList<>();
        
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left1.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left2.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left3.png"));
        yellowEnLeftFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left4.png"));
        
    }
    
    
    
    private void loadBombFrames(){
        
        
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb1.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb2.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb3.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb4.png"));

        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb5.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb6.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb7.png"));
        bombFramesList.add(loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb8.png"));
        
        
    }
    
    
    private void loadExplosionFrames(){
        
        
        explosionC = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "centre.png");

        explosionH = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "horizontal.png");
        explosionV = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "vertical.png");
        
        
    }
    
    
    private void loadTiles(){
        
        
        //walls
        solidw = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "wall" + File.separator + "solid.png");
        brokenw = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "broken" + File.separator + "broken.png");
        emptyw = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "empty" + File.separator + "empty.png");
        goalw = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "goal" + File.separator + "goal.png");

      
    }
    
    
    private void loadIcons(){
        
        
         //icons
        life = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "icons" + File.separator + "player.png");
        clock = loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "icons" + File.separator + "clock.png");
        
        
        
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
                      
                  Integer[] playerLevelStartPos = pStartPlayerPosLevelList.get(levelIndex);
                  
                  map[playerLevelStartPos[0]][playerLevelStartPos[1]] = 'P';
                  
                  int totalLivesBeforeLost = bombGuy.getLives();
                  
                  bombGuy = new BombGuy();
                  bombGuy.setLives(totalLivesBeforeLost - 1);
                  
                  redEnemy = new RedEnemy();
                  yellEnemy = new YellowEnemy();
                  
                  isPlayerInMap = true;
                  
                  }
        
        
    }
    
    
    private void positionBombAnimation(int i, int j){
        
        if(bomb.bombX == j && bomb.bombY == i)
                                    image(bombAnimation.play(bombFramesList),bomb.bombX*32,(bomb.bombY*32) + Map.OFFSET);
        
        
    }
    
    
    
    
    private void removeExplosionEffectAfterExplosion(){
        
        
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
                                      
                                      
                                  }, bomb.ExplosionTime);
                                  
                                  
                              }
        
        
    }
    
    
    private void placeBomb(int i, int j){
        
        
        bomb.isExploded = false;
                              bomb.bombX = j;
                              bomb.bombY = i;

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
        text(bombGuy.getLives(),190,40);
        image(life,150,16);
        
        
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
        
          image(clock,270,16);
        
        
    }
    
    
    private void loadPlayerFramesInLists(){
        
        loadPlayerDownFramesList();
        loadPlayerUpFramesList();
        loadPlayerRightFramesList();
        loadPlayerLeftFramesList();
        
        
    }
    
    
    
    private void loadRedEnemyFramesInLists(){
        
        
        loadRedEnemyUpFrames();
        loadRedEnemyDownFrames();
        loadRedEnemyLeftFrames();
        loadRedEnemyRightFrames();
        
        
    }
    
    
    private void loadYellowEnemyFramesInLists(){
        
        
        
        loadYellowEnemyDownFrames();
        loadYellowEnemyUpFrames();
        loadYellowEnemyLeftFrames();
        loadYellowEnemyRightFrames();
        
        
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
    
    
}
