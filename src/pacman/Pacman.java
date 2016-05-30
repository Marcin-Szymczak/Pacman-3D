package pacman;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
public class Pacman extends JFrame {
    public enum Kierunek {
        Gora, Dol, Lewo, Prawo
    }
    
    public Plansza plansza;
    public Player player;
    Kierunek kierunek;
    public int klawiszGora = KeyEvent.VK_W;
    public int klawiszDol = KeyEvent.VK_S;
    public int klawiszLewo = KeyEvent.VK_A;
    public int klawiszPrawo = KeyEvent.VK_D;
    private Timer timer;
    
    class Krok extends TimerTask {
        public void run() {
            if(null != kierunek) switch (kierunek) {
                case Gora: 
                    if(plansza.mapa[player.getY()/plansza.wlk -1][player.getX()/plansza.wlk] != pacman.Plansza.Pole.Sciana) player.ruch(0, -1);
                    break;
                case Dol:
                    if(plansza.mapa[player.getY()/plansza.wlk +1][player.getX()/plansza.wlk] != pacman.Plansza.Pole.Sciana) player.ruch(0, 1);
                    break;
                case Lewo: 
                    if(plansza.mapa[player.getY()/plansza.wlk][player.getX()/plansza.wlk -1] != pacman.Plansza.Pole.Sciana) player.ruch(-1, 0);
                    break;
                case Prawo:
                    if(plansza.mapa[player.getY()/plansza.wlk][player.getX()/plansza.wlk +1] != pacman.Plansza.Pole.Sciana) player.ruch(1, 0);
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        g2d.translate(10, 30);
        
        plansza.rysuj(g2d);
        player.rysuj(g2d);
        
        g2d.dispose();
        bs.show();
    }
    
    public class Przyciski extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int klawisz = e.getKeyCode();
            
            if(klawisz == klawiszGora) {
                if(plansza.mapa[player.getY()/plansza.wlk -1][player.getX()/plansza.wlk] != pacman.Plansza.Pole.Sciana) kierunek = Kierunek.Gora;
            }
            if(klawisz == klawiszDol) {
                if(plansza.mapa[player.getY()/plansza.wlk +1][player.getX()/plansza.wlk] != pacman.Plansza.Pole.Sciana) kierunek = Kierunek.Dol;
            }
            if(klawisz == klawiszLewo) {
                if(plansza.mapa[player.getY()/plansza.wlk][player.getX()/plansza.wlk -1] != pacman.Plansza.Pole.Sciana) kierunek = Kierunek.Lewo;
            }
            if(klawisz == klawiszPrawo) {
                if(plansza.mapa[player.getY()/plansza.wlk][player.getX()/plansza.wlk +1] != pacman.Plansza.Pole.Sciana) kierunek = Kierunek.Prawo;
            }
        }
        
        public void keyRelased(KeyEvent e) {
            
        }
        
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    Pacman() {
        super("Pacman 3D - Woźniak Szymczak");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setVisible(true);
        createBufferStrategy(2);
        
        addKeyListener(new Przyciski());
        
        plansza = new Plansza();
        player = new Player();
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 100);
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
        pacman.repaint();
        
        pacman.plansza.zaladuj( "001.p3dm" );
    }
    
}
