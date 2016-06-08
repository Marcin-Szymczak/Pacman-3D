/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 *
 * @author Michał
 */
public class Config {
    Properties prop = new Properties();
    InputStream input = null;
    
    private Hashtable<String, Integer> options = new Hashtable<String, Integer>();   

    void load() {
        try {
            input = new FileInputStream("data/config.properties");

            // load a properties file
            prop.load(input);
            
            options.put("keyUp", Integer.valueOf(prop.getProperty("UP")));
            options.put("keyDown", Integer.valueOf(prop.getProperty("DOWN")));
            options.put("keyLeft", Integer.valueOf(prop.getProperty("LEFT")));
            options.put("keyRight", Integer.valueOf(prop.getProperty("RIGHT")));

        } catch (IOException ex) {
            ex.printStackTrace();
//      } finally {
//          if (input != null) {
//              try {
//                  input.close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
        }    
    }
    
    public int getProp(String properties) {
        return options.get(properties);
    } 
    
    Config() {
        load();
    }
}
