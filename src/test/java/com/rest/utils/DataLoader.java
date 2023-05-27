package com.rest.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;
    private DataLoader(){
        properties=PropertyUtils.propertiesLoader("src/test/resources/data.properties");
    }
    public static DataLoader getInstance(){
        if(dataLoader==null){
            dataLoader=new DataLoader();
        }
        return dataLoader;
    }
    public String get_playlist_id(){
        String pop=properties.getProperty("playlist_id");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"playlist_id");
    }
    public String get_update_playlist_id(){
        String pop=properties.getProperty("update_playlist_id");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"update_playlist_id");
    }
}
