package com.collective.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

     public enum ParamKey{
            BROWSER_NAME("browserName"),
            SELENIUM_HUB("seleniumHub"),
            URL("url");


            String value ="";

             ParamKey(String value){
                this.value = value;
            }

            public String getValueKey(){
                return value;
            }
    }


    public static String getProperty(String propertyName) throws IOException {
        Properties pro = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/Config/config.properties");
        pro.load(fileInputStream);
       ParamKey pr =  ParamKey.valueOf(propertyName.toUpperCase());
       return pro.getProperty(pr.getValueKey());


    }
}
