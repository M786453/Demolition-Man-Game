/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolitionman;

/**
 *
 * @author Ahtesham Sarwar
 */
public class Tile {
    
    public int gridHeight;
    public int gridWidth;
    public int gridSpace;
    public boolean canPass;
    public boolean canBreak;
    public boolean isStartupTile;
    public boolean isGoalTile;
    
    
    public Tile(boolean canPass,
            boolean canBreak,
            boolean isStartupTile,
            boolean isGoalTile){
        
         
        
        gridSpace = 32*32;
        gridWidth = 32;
        gridHeight = 32;
        this.canPass = canPass;
        this.canBreak = canBreak;
        this.isStartupTile = isStartupTile;
        this.isGoalTile = isGoalTile;
        
        
    }
    
    
    
}
