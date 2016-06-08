package pacman;

import com.badlogic.gdx.Gdx;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame{
    //Dimension
    public static int WIDTH;
    public static int HEIGHT;
    
    public static Config config;
//    public static int SCALE = 2;
    
    //Main loop
    private Timer timer;
    
    //State Manager
    public StateManager state;
    public boolean game = false;
    
    class Krok extends TimerTask {
        @Override
        public void run() {
                state.update();
                repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        //if( null == bs ) return;
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        //if( null == g2d ) return;
        g2d.setColor( Color.BLACK );
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        
        state.draw(g2d);
        
        g2d.dispose();
        bs.show();
    }
    
    public class Przyciski extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            state.keyPressed(e.getKeyCode());
        }
        
        public void keyRelased(KeyEvent e) {
            
        }
        
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    public Pacman() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();   
        WIDTH = d.width;
        HEIGHT = d.height;

        setTitle("Pacman 3D - Woźniak Szymczak");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setBounds(50, 50, WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        createBufferStrategy(2);
        setFocusable(true);
        requestFocus();
        
        addKeyListener(new Przyciski());
        
        state = new StateManager();
        config = new Config();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 100);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
    }
}
