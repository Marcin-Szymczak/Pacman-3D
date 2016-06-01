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
import javax.swing.JPanel;

/**
 *
 * @author Michał
 */
public class Panel extends JPanel implements Runnable, KeyListener {
    
    //Dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;
    
    //Main loop
    private Thread thread;
    private boolean running = false;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    
    //Drawing
    private Graphics2D g2d;
    private BufferedImage image;
    
    //State Manager
    private StateManager state;
    
    public void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) image.getGraphics();
        
        state = new StateManager();
    }
    
    public void update() {
        state.update();
    }
    
    public void draw() {
        g2d.clearRect(0, 0, WIDTH, HEIGHT);
        state.draw(g2d);
    }
    
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        g.dispose();
    }
    
    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            running = true;
            addKeyListener(this);
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public void run() {
        init();
        
        long start;
        long elapsed;
        long wait;
        
        while(running) {
            start = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            
            if(wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
    Panel() {
        setFocusable(true);
        requestFocus();
    }
}
