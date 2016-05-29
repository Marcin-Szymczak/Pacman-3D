package pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame {
    
    public Plansza plansza;
    private Timer timer;
    
    class Krok extends TimerTask {
        public void run() {
            repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        g2d.translate(10, 30);
        
        plansza.rysuj(g2d);
        
        g2d.dispose();
        bs.show();
    }
    
    Pacman() {
        super("Pacman 3D - Woźniak Szymczak");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        createBufferStrategy(2);
        
        plansza = new Plansza();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 20);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
        pacman.repaint();
        
        pacman.plansza.zaladuj( "001.p3dm" );
    }
    
}
