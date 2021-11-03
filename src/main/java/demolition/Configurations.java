/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demolition;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
 * Used to configure the levels of Game
 * @author Ahtesham Sarwar
 */
public class Configurations {
    
    
        /**
         * Used to Read configurations from text file
         * @return Return a string of configurations
         */
        public static String readConfig(){
        
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
    
    
    /**
     * Used to configure levels according to given string
     * @param jsonData configurations data in string 
     * @return Return list of levels of Game
     */
    public static ArrayList<Level> configureLevels(String jsonData){
        
        ArrayList<Level> levelList = new ArrayList<>();
        
        try{
            
        if(jsonData != null && !jsonData.isEmpty()){    
        JSONObject obj = JSONObject.parse(jsonData);      
        JSONArray levels = obj.getJSONArray("levels");
        
        BombGuy.lives =  obj.getInt("lives");
        
        for(int i=0;i<levels.size();i++){
            
            JSONObject levelObj = levels.getJSONObject(i);
            Level level = new Level(levelObj.getString("path"),levelObj.getInt("time"));
            
            char[][] level_map = readMapFromFile(level.path);
            
            level.setMap(level_map);
            
            
            if(Map.validateMap(level_map))
            levelList.add(level);
            
            
        }
        
        }
        
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return levelList;
        
    }
    
    
    /**
     * Used to read map from text file
     * @param path Represents the path of map text file
     * @return Return map (2-D Char Array) 
     */
    public static char[][] readMapFromFile(String path){
        
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
                          
                          
                          BombGuy.pStartPlayerPosLevelList.add(playerStartPos);
                          
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
    
    
    
    
    
    
}
