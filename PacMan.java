import java.awt.Color;
import java.awt.Graphics;

public class PacMan{
    int tileSize = 20;
    int x = 14;
    int y = 19;
    int BoardHeight;
    int BoardWidth;
    boolean died = false;
    boolean touchable = true;
    
    // velocity
    int velocityX = 0;
    int velocityY = 0;
    int velocityXT = 0;
    int velocityYT = 0;

    // pacman change counter
    int count = 0;

    //points
    int points = 0;

    // map properties
    MapProperties mapProperties;
    // mapa 
    Map map;

    Color color1;
    

    PacMan(int BoardHeight,int BoardWidth, MapProperties mapProperties){
        this.mapProperties = mapProperties;

        //map
        map = new Map(BoardHeight, BoardWidth, mapProperties.candyMap1);
    }

    public void draw(Graphics g){
        map.drawMap(g);
        g.setColor(color1);
        g.fillOval(x*tileSize-2,y*tileSize-2,24,24);
    }

    public void draw2 (Graphics g){
        map.drawMap(g);
        g.setColor(Color.yellow);
        g.fillOval(x*tileSize-2,y*tileSize-2,24,24);
    }

    public void draw1(Graphics g){
        map.drawMap(g);
        g.setColor(color1);
        if(velocityX==0 && velocityY==0){
            g.fillOval(x*tileSize-2,y*tileSize-2,24,24);
        }
        else{
            if(velocityX == 1 && velocityY == 0){
                g.fillArc(x*tileSize-2,y*tileSize-2,24,24, 45, 270);
            }
            else if(velocityX == -1 && velocityY == 0){
                g.fillArc(x*tileSize-2,y*tileSize-2,24,24, 225, 270);
            }
            else if(velocityX == 0 && velocityY == 1){
                g.fillArc(x*tileSize-2,y*tileSize-2,24,24, 315, 270);
            }
            else if(velocityX == 0 && velocityY == -1){
                g.fillArc(x*tileSize-2,y*tileSize-2,24,24, 135, 270);
            }

        }
        
        
       
    }

    public void move(Point ghost0,Point ghost1,Point ghost2,Point ghost3){
        if(collidingGhost(x, y, ghost0.x, ghost0.y) ||
            collidingGhost(x, y, ghost1.x, ghost1.y)||
            collidingGhost(x, y, ghost2.x, ghost2.y)||
            collidingGhost(x, y, ghost3.x, ghost3.y)){
                if(touchable){
                    died = true;
                    x = 14;
                    y = 19;
                    velocityX = 0;
                    velocityY = 0;
                }
        }
        
        if(collidingCandy(0)){
            mapProperties.candyMap1[28*(y)+x] = 2;
            points++;
        }


        if(colliding(0, 0,3) && x==0 && velocityX == -1){
            x = 28;
        }
        else if(colliding(0, 0,3) && x==27 && velocityX == 1){
            x = 0;
        }
        else if(colliding(velocityXT,velocityYT,1)){
            if(colliding(velocityX, velocityY,1))
            {
                velocityX = 0;
                velocityY = 0;  
            }

        }
        else{
            velocityX = velocityXT;
            velocityY = velocityYT;
        }
        if(count%2==0){
            x += velocityX;
            y += velocityY;
        }


        count++;
    }

    public boolean collidingGhost(int x1, int y1, int x2, int y2){
        if(x1==x2 && y1==y2){
            return true;
        }
        else return false;
        
    }
    public boolean colliding(int x1, int y1, int num){
        if(mapProperties.map1[28*(y+y1)+x+x1]==num){
            return true;
        }
        return false;
    }
    public boolean collidingCandy( int num){
        if(mapProperties.candyMap1[28*(y)+x]==num){
            return true;
        }
        return false;
    }


}
