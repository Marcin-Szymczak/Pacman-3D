package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

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
    private Direction future;
    private Plansza level;
    private int score;
    private Sounds sounds;
    
    public Player( Plansza level, double x, double y ) {
        posX = x;//13.0;
        posY = y;//17.0;
        direction = Direction.Up;
        future = direction;
        this.level = level;
        score=0;
        sounds = new Sounds();
    }
    
    public void rysuj(Graphics2D g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillOval(Pacman.WIDTH-Plansza.WIDTH*Plansza.wlk-5+(int)posX*Plansza.wlk, Pacman.HEIGHT-Plansza.HEIGHT*Plansza.wlk-5+(int)posY*Plansza.wlk, Plansza.wlk, Plansza.wlk);
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setDirection( Direction direction )
    {
        //Dir dir = new Dir( direction );
        //if( level.jakiePole( getTX()+dir.dx, getTY()+dir.dy ) != Plansza.Pole.Sciana )
            //this.direction = direction;
        future = direction;
            
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Wykonuje jeden krok gracza
     * 
     * @param plansza 
     */
    public void update() {
        
        //posX += dx*pacman.Plansza.wlk;
        //posY += dy*pacman.Plansza.wlk;
        Dir dir = new Dir(direction);
        Dir fut = new Dir(future);
        
        if( direction != future && level.jakiePole( getTX()+fut.dx, getTY()+fut.dy ) != Plansza.Pole.Sciana )
        {
            direction = future;
            dir = fut;
        }
        
        double npx = (posX+dir.dx);
        double npy = (posY+dir.dy);
        
        if( npx >= Plansza.WIDTH )
            npx = npx - Plansza.WIDTH;
        if( npx < 0 )
            npx = npx + Plansza.WIDTH;
        
        if( npy >= Plansza.HEIGHT )
            npy = npy - Plansza.HEIGHT;
        if( npy < 0 )
            npy = npy + Plansza.HEIGHT;
        
        if( level.jakiePole( (int)npx, (int)npy ) != Plansza.Pole.Sciana )
        {
            posX = npx;
            posY = npy;
        }
        
        if( level.jakiePole( getTX(), getTY() ) == Plansza.Pole.Punkt )
        {
            level.ustaw( getTX(), getTY(), Plansza.Pole.Puste );
            score++;
        }
        
        if( level.jakiePole( getTX(), getTY() ) == Plansza.Pole.Bonus )
        {
            level.ustaw( getTX(), getTY(), Plansza.Pole.Puste);
            Plansza.fear = 100;
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
