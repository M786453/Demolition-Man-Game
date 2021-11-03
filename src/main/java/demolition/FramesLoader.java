/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Used to load Frames of Game Objects into Game
 * @author Ahtesham Sarwar
 */
public class FramesLoader {
    
    private PApplet p;
    
    public PImage solidw,brokenw,emptyw,goalw,life,clock,explosionC,explosionH,explosionV;
    
    public ArrayList<PImage> playerDownFramesList;
    public ArrayList<PImage> playerUpFramesList;
    public ArrayList<PImage> playerLeftFramesList;
    public ArrayList<PImage> playerRightFramesList;
    
    
    public ArrayList<PImage> redEnDownFramesList;
    public ArrayList<PImage> redEnUpFramesList;
    public ArrayList<PImage> redEnLeftFramesList;
    public ArrayList<PImage> redEnRightFramesList;
    
    
    public ArrayList<PImage> yellowEnDownFramesList;
    public ArrayList<PImage> yellowEnUpFramesList;
    public ArrayList<PImage> yellowEnLeftFramesList;
    public ArrayList<PImage> yellowEnRightFramesList;
    
    public ArrayList<PImage> bombFramesList;
    
    FramesLoader(PApplet parent){
        
        
       this.p = parent; // this keeps reference to parent PApplet class
       
        
    }
    
    
    
    /**
     * Load Frames of Red Enemy in their respective lists
     */
    public void loadRedEnemyFramesInLists(){
        
        
        loadRedEnemyUpFrames();
        loadRedEnemyDownFrames();
        loadRedEnemyLeftFrames();
        loadRedEnemyRightFrames();
        
        
    }
    
    /**
     * Load Frames of Yellow Enemy in their respective lists
     */
    public void loadYellowEnemyFramesInLists(){
        
        
        
        loadYellowEnemyDownFrames();
        loadYellowEnemyUpFrames();
        loadYellowEnemyLeftFrames();
        loadYellowEnemyRightFrames();
        
        
    }
    
     /**
      * Load Frames of Player Frames in their respective lists
      */
     public void loadPlayerFramesInLists(){
        
        loadPlayerDownFramesList();
        loadPlayerUpFramesList();
        loadPlayerRightFramesList();
        loadPlayerLeftFramesList();
        
        
    }
    
    
    /**
     * Load Player Down Direction Frames in it's List
     */
    private void loadPlayerDownFramesList(){
        
        playerDownFramesList = new ArrayList<>();
        
        
        playerDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player1.png"));

        playerDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player2.png"));

        playerDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player3.png"));

        playerDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player4.png"));

        
        
        
    }
    
    
    /**
     * Load Player Right Direction Frames in it's List
     */
    private void loadPlayerRightFramesList(){
        
        playerRightFramesList = new ArrayList<>();
        
        playerRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right1.png"));

        playerRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right2.png"));

        playerRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right3.png"));

        playerRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_right4.png"));
        
        
        
    }
    
    
    /**
     * Load Player Left Direction Frames in it's List
     */
    private void loadPlayerLeftFramesList(){
        
        playerLeftFramesList = new ArrayList<>();        
        
        playerLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left1.png"));

        playerLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left2.png"));

        playerLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left3.png"));

        playerLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_left4.png"));

        
        
    }
    
    
    /**
     * Load Player Up Direction Frames in it's List
     */
    private void loadPlayerUpFramesList(){
        
        
        playerUpFramesList = new ArrayList<>();
        
        playerUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up1.png"));

        playerUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up2.png"));

        playerUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up3.png"));

        playerUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "player" + File.separator + "player_up4.png"));

        
        
        
    }
    
    
    
    /**
     * Load Frames of Bomb in it's list
     */
    public void loadBombFrames(){
        
        bombFramesList = new ArrayList<>();
        
        
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb1.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb2.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb3.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb4.png"));

        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb5.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb6.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb7.png"));
        bombFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "bomb" + File.separator + "bomb8.png"));
        
        
    }
    
    
    /**
     * Load Frames(images) of Explosion
     */
    public void loadExplosionFrames(){
        
        
        explosionC = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "centre.png");

        explosionH = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "horizontal.png");
        explosionV = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "explosion" + File.separator + "vertical.png");
        
        
    }
    
    
    /**
     * Load Frames(images) of all types of tiles
     */
    public void loadTiles(){
        
        
        //walls
        solidw = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "wall" + File.separator + "solid.png");
        brokenw = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "broken" + File.separator + "broken.png");
        emptyw = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "empty" + File.separator + "empty.png");
        goalw = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "goal" + File.separator + "goal.png");

      
    }
    
    
     /**
      * Load Frames(images) of icons of Game
      */
     public void loadIcons(){
        
        
         //icons
        life = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "icons" + File.separator + "player.png");
        clock = p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "icons" + File.separator + "clock.png");
        
        
        
    }
     
     
    /**
     * Load Frames of Red Enemy Down Direction in it's respective list
     */ 
    private void loadRedEnemyDownFrames(){
        
        
        redEnDownFramesList = new ArrayList<>();
        
        
         redEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down1.png"));
        redEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down2.png"));
        redEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down3.png"));
        redEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_down4.png"));

        
    }
    
    
    /**
     * Load Frames of Red Enemy Up Direction in it's respective list
     */
    private void loadRedEnemyUpFrames(){
        
        
        redEnUpFramesList = new ArrayList<>();
        
        
        redEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up1.png"));
        redEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up2.png"));
        redEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up3.png"));
        redEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_up4.png"));
        
    }
    
    
    /**
     * Load Frames of Red Enemy Right Direction in it's respective list
     */
    private void loadRedEnemyRightFrames(){
        
        
        redEnRightFramesList = new ArrayList<>();
        
        redEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right1.png"));
        redEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right2.png"));
        redEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right3.png"));
        redEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_right4.png"));
        
        
    }
    
    
    /**
     * Load Frames of Red Enemy Left Direction in it's respective list
     */
    private void loadRedEnemyLeftFrames(){
        
        redEnLeftFramesList = new ArrayList<>();
        
        redEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left1.png"));
        redEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left2.png"));
        redEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left3.png"));
        redEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "red_enemy" + File.separator + "red_left4.png"));
        
    }
    
    
    /**
     * Load Frames of Yellow Enemy Down Direction in it's respective list
     */
    private void loadYellowEnemyDownFrames(){
        
        
        yellowEnDownFramesList = new ArrayList<>();
        
        
        yellowEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down1.png"));
        yellowEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down2.png"));
        yellowEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down3.png"));
        yellowEnDownFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_down4.png"));

      
    }
    
    /**
     * Load Frames of Yellow Enemy Up Direction in it's respective list
     */
    private void loadYellowEnemyUpFrames(){
        
        yellowEnUpFramesList = new ArrayList<>();
        
        
        
         yellowEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up1.png"));
        yellowEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up2.png"));
        yellowEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up3.png"));
        yellowEnUpFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_up4.png"));
        
        
    }
    
    
    /**
     * Load Frames of Yellow Enemy Right Direction in it's respective list
     */
    private void loadYellowEnemyRightFrames(){
        
        
        yellowEnRightFramesList = new ArrayList<>();
        
         yellowEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right1.png"));
        yellowEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right2.png"));
        yellowEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right3.png"));
        yellowEnRightFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_right4.png"));
        
        
    }
    
    
    /**
     * Load Frames of Yellow Enemy Left Direction in it's respective list
     */
    private void loadYellowEnemyLeftFrames(){
        
        yellowEnLeftFramesList = new ArrayList<>();
        
        yellowEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left1.png"));
        yellowEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left2.png"));
        yellowEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left3.png"));
        yellowEnLeftFramesList.add(p.loadImage(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "yellow_enemy" + File.separator + "yellow_left4.png"));
        
    }
    
    
}
