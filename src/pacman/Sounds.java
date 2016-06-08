/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author Michał
 */
public class Sounds {
    public enum Sound {
        Intro, WakaWaka, Fruit, Ghost, ExtraMan, Intermission, Death
    }
    AudioInputStream audioInputStream;
    
    public void play(Sound filename) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("data/"+(filename).toString().toLowerCase()+".wav"));
            AudioFormat format = audioInputStream.getFormat();
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = .04D;
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSound(Sound filename) {
        play(filename);
    }
    
    Sounds(){}
}