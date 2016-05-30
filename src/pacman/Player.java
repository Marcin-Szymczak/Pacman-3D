package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Michał
 */
public class Player {
    private int posX, posY;
    
    public Player() {
        posX = pacman.Plansza.wlk*13;
        posY = pacman.Plansza.wlk*17;
    }
    
    public void rysuj(Graphics2D g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillOval(posX, posY, pacman.Plansza.wlk, pacman.Plansza.wlk);
    }
    
    public void ruch(int dx, int dy) {
        posX += dx*pacman.Plansza.wlk;
        posY += dy*pacman.Plansza.wlk;
    }
    
    public int getX() {
        return posX;
    }
    
    public int getY() {
        return posY;
    }
}
