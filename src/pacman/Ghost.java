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
    Image img;
    Path path;
    
    enum Strategy
    {
        Follow, Intercept, Flee, Random
    }
    
    Ghost(Plansza level, Player player, double x, double y, int homex, int homey )
    {
        this.target = player;
        this.level = level;
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
            
            if(Plansza.fear == 0) path.find( getTX(), getTY(), target.getTX(), target.getTY());
            else path.find( getTX(), getTY(), 1, 1);

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
        g2d.drawImage( img, (int)posX*Plansza.wlk, (int)posY*Plansza.wlk, null );

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
