/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author marcin
 */
public class Ghost {
    double posX;
    double posY;
    int homex;
    int homey;
    int tick;
    Player target;
    Plansza level;
    Strategy strategy;
    Image img;
    Path path;
    
    enum Strategy
    {
        Follow, Intercept, Flee, Random
    }
    
    Ghost(Plansza level, Player player, Strategy strategy, double x, double y, int homex, int homey )
    {
        this.target = player;
        this.level = level;
        this.strategy = strategy;
        img = new ImageIcon("data/ghost.png").getImage();
        posX = x;
        posY = y;
        path = new Path();
        tick = 0;
        this.homex = homex;
        this.homey = homey;
    }
    
    public void update()
    {
        if( tick == 0 )
        {
            path.setData( level.zbudujWezly() );
            
            if(Plansza.fear == 0 && strategy == Strategy.Follow) path.find( getTX(), getTY(), target.getTX(), target.getTY());
            else if (Plansza.fear == 0 && strategy == Strategy.Intercept) path.find( getTX(), getTY(), crossroad(target)[0], crossroad(target)[1]);
            else path.find( getTX(), getTY(), homex, homey);

            if( null != path.data && path.data.length >= 1 )
            {
                Path.Node n = path.data[ path.data.length-2 ]; //path.data[0] to miejsce w ktorym jestesmy aktualnie
                this.posX = (double)n.x;
                this.posY = (double)n.y;
            }
        }
        tick = (tick+1)%2;
    }
    
    public void rysuj( Graphics2D g2d )
    {
        //path.rysuj(g2d,true,false);
        g2d.setColor( Color.WHITE );
        g2d.drawImage( img, Pacman.WIDTH-Plansza.WIDTH*Plansza.wlk-5+(int)posX*Plansza.wlk, Pacman.HEIGHT-Plansza.HEIGHT*Plansza.wlk-5+(int)posY*Plansza.wlk, null );

    }
    
    public int[] crossroad(Player player) {
        int[] pos = new int[2];
        pos[0] = player.getTX();
        pos[1] = player.getTY();
        Player.Direction dir = player.getDirection();
        
        while(!isCrossroad(pos[0], pos[1])) {
            if(null != dir) switch (dir) {
                case Up:
                    pos[1]++;
                    break;
                case Down:
                    pos[1]--;
                    break;
                case Left:
                    pos[0]--;
                    break;
                case Right:
                    pos[0]++;
                    break;
                default:
                    break;
            }
        }
        
        return pos;
    }
    
    public boolean isCrossroad(int TX, int TY) {
        int neighbours = 0;
        if(level.jakiePole(TX, TY) != Plansza.Pole.Sciana) {
            for (int dx = 0; dx < 2; dx++) {
                for (int dy = 0; dy < 2; dy++) {
                    if(level.jakiePole(TX+dx, TY+dy) != Plansza.Pole.Sciana) {
                        neighbours++;
                    } 
                }
            }
        }
        
        if( neighbours > 3 ) return true;
        return false;
    }
    
    public double getX()
    {
        return posX;
    }
    
    public double getY()
    {
        return posY;
    }
    
    public int getTX()
    {
        return (int)posX;
    }
    
    public int getTY()
    {
        return (int)posY;
    }
}
