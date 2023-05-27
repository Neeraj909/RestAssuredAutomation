package com.rest.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertyUtils {
    public static Properties propertiesLoader(String filePath) {
        BufferedReader reader1;
        Properties properties = new Properties();
        try{
           reader1=new BufferedReader(new FileReader(filePath));
           try{
               properties.load(reader1);
           }
           catch (Exception e){
               e.printStackTrace();
               throw new RuntimeException("failed load properties"+filePath);
           }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("properties file not found "+filePath);

        }
      return properties;
    }
}
