/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

/**
 *
 * @author Ahtesham Sarwar
 */
public class Level {
    
    public String path;
    public int time;
    public char[][] mapArray;
    public char[][] originalMap;
    
    
    public Level(String path,int time){
        
        this.path = path;
        this.time = time;
        mapArray = null;
        
        
    }
    
    
    public void setMap(char[][] map){
        
        mapArray = map;
        
        
        originalMap = new char[Map.ROWS][Map.COLUMNS];
        
        for(int i=0;i<Map.ROWS;i++){
            
            for(int j=0;j<Map.COLUMNS;j++){
                
            
                originalMap[i][j] =  mapArray[i][j];
                       
                
            }
            
        }
        
        
        
    }
    
   
    
    public void resetLevel(){
        
        char[][] resetedMap = new char[Map.ROWS][Map.COLUMNS];
        
        for(int i=0;i<Map.ROWS;i++){
            
            for(int j=0;j<Map.COLUMNS;j++){
                
            
                resetedMap[i][j] =  originalMap[i][j];
                       
                
            }
            
        }
        
        mapArray = resetedMap;
        
    }
    
}
