package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Michał
 */
public class Player {
    public enum Direction
    {
        Up, Down, Left, Right;
    }
    public class Dir
    {
        int dx;
        int dy;
        
        Dir( Direction direction )
        {
            dx=0;
            dy=0;
            switch( direction )
            {
                case Up:
                    dy = -1;
                    break;
                case Down:
                    dy = 1;
                    break;
                case Left:
                    dx = -1;
                    break;
                case Right:
                    dx = 1;
                    break;
            }
        }
    }
    
    private double posX, posY;
    private Direction direction;
    private Plansza level;
    
    public Player( Plansza level ) {
        posX = 13.0;
        posY = 17.0;
        direction = Direction.Up;
        this.level = level;
    }
    
    public void rysuj(Graphics2D g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillOval((int)posX*Plansza.wlk, (int)posY*Plansza.wlk, pacman.Plansza.wlk, pacman.Plansza.wlk);
    }
    
    public void setDirection( Direction direction )
    {
        Dir dir = new Dir( direction );
        if( level.jakiePole( getTX()+dir.dx, getTY()+dir.dy ) != Plansza.Pole.Sciana )
            this.direction = direction;
    }
    
    /**
     * Wykonuje jeden krok gracza
     * 
     * @param plansza 
     */
    public void ruch() {
        
        //posX += dx*pacman.Plansza.wlk;
        //posY += dy*pacman.Plansza.wlk;
        Dir dir = new Dir(direction);
        
        double npx = (posX+dir.dx);
        double npy = (posY+dir.dy);
        if( level.jakiePole( (int)npx, (int)npy ) != Plansza.Pole.Sciana )
        {
            posX = npx;
            posY = npy;
        }
        
    }
    
    public double getX() {
        return posX;
    }
    
    public double getY() {
        return posY;
    }
    
    /** Zwraca pozycje wyrażoną w kafelkach
     * 
     * @return 
     */
    public int getTX()
    {
        return (int)posX;
    }
    /** Zwraca pozycje wyrażoną w kafelkach
     * 
     * @return 
     */
    public int getTY()
    {
        return (int)posY;
    }
}
