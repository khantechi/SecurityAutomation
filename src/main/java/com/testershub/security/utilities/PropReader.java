package com.testershub.security.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReader {

    public String getProperty(String keyName){
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(String.valueOf(System.class.getResourceAsStream("testData.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(keyName);
    }


}
