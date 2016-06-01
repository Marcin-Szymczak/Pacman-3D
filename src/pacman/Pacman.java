package pacman;

import javax.swing.JFrame;

/**
 *
 * @author Michał i Marcin
 */
<<<<<<< HEAD
public class Pacman extends JFrame {
   
    public Plansza plansza;
    public Player player;
    public Ghost[] ghost;
    
    public int klawiszGora = KeyEvent.VK_W;
    public int klawiszDol = KeyEvent.VK_S;
    public int klawiszLewo = KeyEvent.VK_A;
    public int klawiszPrawo = KeyEvent.VK_D;
    private Timer timer;
    
    class Krok extends TimerTask {
        @Override
        public void run() {
            player.update();
            
            for (Ghost g : ghost)
            {
                if( null != g )
                    g.update();
            }
            plansza.update();
            repaint();
        }
    }
    
    @Override
    public void paint(Graphics g) {
        BufferStrategy bs = this.getBufferStrategy();
        //if( null == bs ) return;
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
        //if( null == g2d ) return;
        g2d.setColor( Color.DARK_GRAY );
        g2d.fillRect(0, 0, 800, 600);
        g2d.translate(10, 30);
        
        plansza.rysuj(g2d);
        player.rysuj(g2d);

        for(Ghost gh : ghost)
        {
            if( null != gh )
                gh.rysuj(g2d);
        }

        g2d.setColor( Color.WHITE );
        g2d.setFont(new Font("Arial",Font.BOLD,20));
        g2d.drawString("X: " + player.getTX(), 500, 20);
        g2d.drawString("Y: "+ player.getTY(), 500, 40);
        g2d.drawString("SCORE: "+ player.getScore(), 500, 60 );
        
        g2d.dispose();
        bs.show();
    }
    
    public class Przyciski extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int klawisz = e.getKeyCode();
            
            if(klawisz == klawiszGora) {
                player.setDirection( Player.Direction.Up );
            }
            if(klawisz == klawiszDol) {
                player.setDirection( Player.Direction.Down );
            }
            if(klawisz == klawiszLewo) {
                player.setDirection( Player.Direction.Left );
            }
            if(klawisz == klawiszPrawo) {
                player.setDirection( Player.Direction.Right );
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
        player = new Player(plansza, 13.0, 17.0 );
        ghost = new Ghost[2];
        ghost[0] = new Ghost(plansza, player, 13.0, 15.0, 1, 1 );
        ghost[1] = new Ghost(plansza, player, 14.0, 15.0, WIDTH-1, HEIGHT-1 );
        //ghost[2] = null;
        //ghost[3] = null;
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new Krok(), 0, 100);
=======
public class Pacman extends JFrame{
    public static Panel frame;
    
    Pacman() { 
        frame = new Panel();
>>>>>>> refs/remotes/origin/Michal
    }

    public static void main(String[] args) {
        Pacman pacman = new Pacman();
    }
}
