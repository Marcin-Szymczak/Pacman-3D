/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 *
 * @author Michał
 */
public class Game extends State{
    
    public Plansza plansza;
    public Player player;
    public Ghost[] ghost;
    private Sounds sounds;
    
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;
    
    public void update() {
            player.update();
            
            for (Ghost g : ghost)
            {
                if( null != g )
                    g.update();
            }
            plansza.update();
    }
    
    public void draw(Graphics2D g2d) {
        g2d.setColor( Color.BLACK );
        //g2d.fillRect(0, 0, pacman.Panel.WIDTH*pacman.Panel.SCALE, pacman.Panel.HEIGHT*pacman.Panel.SCALE);
        g2d.fillRect(0, 0, Pacman.WIDTH, Pacman.HEIGHT);
        
        plansza.rysuj(g2d);
        player.rysuj(g2d);

        for(Ghost gh : ghost)
        {
            if( null != gh )
                gh.rysuj(g2d);
        }
        
        g2d.setColor( Color.BLACK );
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("X: " + player.getTX(), 500, 20);
        g2d.drawString("Y: "+ player.getTY(), 500, 45);
        g2d.drawString("SCORE: "+ player.getScore(), 500, 60 );
    }
    
    public void keyPressed(int key) {
        if(key == keyUp) {
            player.setDirection( Player.Direction.Up );
        }
        if(key == keyDown) {
            player.setDirection( Player.Direction.Down );
        }
        if(key == keyLeft) {
            player.setDirection( Player.Direction.Left );
        }
        if(key == keyRight) {
            player.setDirection( Player.Direction.Right );
        }
        if(key == KeyEvent.VK_ESCAPE) {
            state.setState(pacman.StateManager.MENUSTATE);  
            pacman.StateManager.states.remove(pacman.StateManager.GAMESTATE);            
        }
    }
    
    public void keyReleased(int key){
        
    }
    
    Game(StateManager state) {
        this.state = state;
        
        plansza = new Plansza();
        player = new Player(plansza, 13.0, 17.0 );
        ghost = new Ghost[4];
        ghost[0] = new Ghost(plansza, player, Ghost.Strategy.Follow, 13.0, 13.0, 1, 1 );
        ghost[1] = new Ghost(plansza, player, Ghost.Strategy.Follow, 14.0, 13.0, 26, 1 );
        ghost[2] = new Ghost(plansza, player, Ghost.Strategy.Follow, 13.0, 12.0, 1, 29 );
        ghost[3] = new Ghost(plansza, player, Ghost.Strategy.Follow, 14.0, 12.0, 26, 29 );
        
        plansza.zaladuj( "data/001.p3dm" );
           
        keyUp = Pacman.config.getProp("keyUp");
        keyDown = Pacman.config.getProp("keyDown");
        keyLeft = Pacman.config.getProp("keyLeft");
        keyRight = Pacman.config.getProp("keyRight");
        
//        AudioInputStream audioInputStream;
//        try {
//            audioInputStream = AudioSystem.getAudioInputStream(new File("data/intro.wav"));
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            double gain = .04D; // number between 0 and 1 (loudest)
//            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
//            gainControl.setValue(dB);
//            clip.start();
//        } catch (UnsupportedAudioFileException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (LineUnavailableException ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
