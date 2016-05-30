/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Console;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;

/** 
 *
 * @author marcin
 */
public class Path {
    static enum State
    {
        Blank,
        Open,
        Closed,
        Discarded,
        Path
    }
    
    static public class Node implements Comparable<Node>
    {
        int x;
        int y;
        
        State state;
        int cost;
        int estimated;
        Node parent;
        Node[] neighbours;

        @Override
        public int compareTo(Node t) {
            return cost + estimated - t.cost - t.estimated;
        }
    }
    
    Node[] data;
    Path()
    {
    
    }
    Path( Node[] data )
    {
        setData( data );
    }
    
    void setData( Node[] data )
    {
        this.data = data;
    }
    
    void rysuj( Graphics2D g2d )
    {
        if( null == data ) return;
        
        for( Node n : data )
        {
            int wlk = Plansza.wlk;
            g2d.setColor(Color.PINK);
            g2d.fillRect( n.x*wlk+wlk/3, n.y*wlk+wlk/3, wlk-wlk/3*2, wlk-wlk/3*2 );
            if( null != n.parent )
                g2d.drawLine(n.x*wlk+wlk/2, n.y*wlk+wlk/2, n.parent.x*wlk+wlk/2, n.parent.y*wlk+wlk/2 );
            for( Node neighbour : n.neighbours )
            {
                if( null != neighbour )
                {
                    g2d.setColor( Color.GREEN );
                    g2d.drawLine( n.x*wlk+wlk/2, n.y*wlk+wlk/2, neighbour.x*wlk+wlk/2, neighbour.y*wlk+wlk/2);
                }
            }
        }
    }
    
    /**
     * Znajduje droge ze startx, starty do x,y
     * @param startx - x wspolrzedna poczatku
     * @param starty - y wspolrzedna poczatku
     * @param x - x wspolrzedna konca
     * @param y - y wspolrzedna konca
     */
    public boolean find( int startx, int starty, int x, int y )
    {
        if( null == data ) return false;
        ArrayList<Node> open = new ArrayList<>();
        Node start=null;
        Node end=null;
        for( Node n : data )
        {
            if( n.x == startx && n.y == starty ){
                start = n;
                break;
            }
        }
        if(null==start)
        {
            //System.out.print("Nie mozna szukac drogi z niewlasciwego punktu!");
            return false;
        }
        
        open.add(start);
        start.state = State.Open;
        start.cost = 0;
        start.estimated = (int)sqrt( (start.x - x)^2 + (start.y - y)^2 );
        start.parent = null;
        
        boolean praca = true;
        
        while( open.size() > 0 )
        {
            Node node = open.get(0);
            node.state = State.Closed;
            for( Node n : node.neighbours )
            {
                //Na tym etapie algorytmu spodziewam sie tylko wezlow
                //o stanach Blank, Open i Closed
                if( null != n && n.state != State.Closed )
                {
                    int new_estimated = (int)sqrt( (n.x - x)^2 + (n.y - y)^2 );
                    int new_cost = node.cost + 1;
                    if( n.state == State.Blank )
                            open.add( n );
                    if( n.state == State.Blank || n.cost > new_cost )
                    {
                        n.parent = node;
                        n.cost = new_cost;
                        n.estimated = new_estimated;
                    }
                    if( n.x == x && n.y == y )
                    {
                        //System.out.print("Znalazlem koniec!");
                        end = n;
                        praca = false;
                        break;
                    }
                }
            }
            

            //Wyrzucam aktualny element
            Node temp = node;
            open.set( open.indexOf(node), open.get( open.size()-1 ) );
            open.remove( open.size()-1 );
            
            Collections.sort( open );
            if(!praca) break;
        }
        
        if( null == end )
        {
            data = null;
            //System.out.print("Brak drogi! :(");
            return false;
        }
        else
        {
            ArrayList<Node> pathconstruct = new ArrayList<>();
            //Rekonstrukcja drogi za pomoca otrzymanych danych
            while( null != end )
            {
                end.state = State.Path;
                pathconstruct.add( end );
                end = end.parent;
            }
            data = pathconstruct.toArray( new Node[ pathconstruct.size()] );
            //System.out.print("Droga zrekonstruowana");
        }
        return true;
    }
}
