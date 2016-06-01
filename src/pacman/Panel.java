/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Michał
 */
public class Panel extends JPanel {
    
    //Dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;
    
    //Main loop
    private Timer timer;
    
    //Drawing
    private Graphics2D g2d;
    private BufferedImage image;
    
    //State Manager
    private StateManager state;
    
    public void init() {
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) image.getGraphics();
        
        state = new StateManager(g2d);
    }
    
    public void update() {
        state.update(g2d);
    }
    
    public void draw() {
        g2d.clearRect(0, 0, 800, 600);
        state.draw(g2d);
    }
    
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, 800, 600, null);
        g.dispose();
    }
    
    class Krok extends TimerTask {
        @Override
        public void run() {
            update();
            draw();
            drawToScreen();
        }
    }
    
    public void keyPressed(KeyEvent key) {
        state.keyPressed(key.getKeyCode());
    }
    
    public void keyReleased(KeyEvent key) {
        state.keyReleased(key.getKeyCode());
    }
    
    public void keyTyped(KeyEvent key) {
    }
    
    public Panel() {
        init();
    
        setFocusable(true);
        requestFocus();
        
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 100);
    }
}
