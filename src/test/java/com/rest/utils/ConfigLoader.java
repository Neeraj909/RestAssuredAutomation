package com.rest.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;
    private ConfigLoader(){
        properties=PropertyUtils.propertiesLoader("src/test/resources/config.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }
    public String getClientId(){
        String pop=properties.getProperty("client_id");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"client_id");
    }
    public String get_client_secret(){
        String pop=properties.getProperty("client_secret");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"client_secret");
    }

    public String get_refresh_token(){
        String pop=properties.getProperty("refresh_token");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"refresh_token");
    }
    public String get_grant_type(){
        String pop=properties.getProperty("grant_type");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"grant_type");
    }

    public String get_user_id(){
        String pop=properties.getProperty("user_id");
        if(pop!=null) return pop;
        else throw new RuntimeException("Property variable not available"+"user_id");
    }
}
