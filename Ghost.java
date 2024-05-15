import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ghost {

    int x;
    int y;
    int tileSize = 20;
    Color color;

    //randomizer
    Random random;
    int randomDirection;

    //velocity
    int velocityX = 0;
    int velocityY = -1;

    // map properties
    MapProperties mapProperties;

    //counter
    int counter = 0;
    int counter2 = 0;
    int time;

    //path
    int[] pathX;
    int[] pathY;
    
    Ghost(int x, int y, Color color, MapProperties mapProperties, int time, int[] pathX, int[] pathY){
        this.mapProperties = mapProperties;
        this.x = x;
        this.y = y;
        this.color = color;
        this.time = time;
        this.pathX = pathX;
        this.pathY = pathY;
        random = new Random();
    }

    public void draw(Graphics g){
        g.setColor(color);
        

        g.setColor(color);
        g.fillOval(x*tileSize, y*tileSize, 20, 20);
        g.fillRect(x * tileSize, y * tileSize+10, 20, 10);
        g.setColor(Color.black);
        g.fillRect(x*tileSize+4, y*tileSize+4, 5, 5);
        g.fillRect(x*tileSize+11, y*tileSize+4, 5, 5);

        
        

        
        if(counter == time){
            move();
        }
        else if(counter == time -1){
            if(counter2 < 5){
                x = pathX[counter2];
                y = pathY[counter2];
                counter2++;
            }
            else if(counter2 == 5){
                counter++;
            }
        }
        else{
            counter++;
            move2();
        }
        
    }
    public void draw1(Graphics g){
        g.setColor(color);
        g.fillOval(x*tileSize, y*tileSize, 20, 20);
        g.fillRect(x * tileSize, y * tileSize+10, 20, 10);
        g.setColor(Color.black);
        g.fillRect(x*tileSize+4, y*tileSize+4, 5, 5);
        g.fillRect(x*tileSize+11, y*tileSize+4, 5, 5);
    }

    public void move2(){
        if(colliding(0,-1, 1)){
            velocityX = 0;
            velocityY = 1;
        }
        else if(colliding(0,1, 1)){
            velocityX = 0;
            velocityY = -1;
        }
        x += velocityX;
        y += velocityY;
        
    }
    public void move(){
        if(colliding(0, 0, 3) && velocityX == 1){
            x = 0;
            velocityX = 1;
        }
        else if(colliding(0, 0, 3) && velocityX == -1){
            x = 27;
            velocityX = -1;
        }
        else if(colliding(velocityX, velocityY, 1)){
            int go = 0;
            if(velocityX==0){
                while(go != 1){

                    randomDirection = random.nextInt(6);
                    if( randomDirection % 3 == 0 && !colliding(0, velocityY*(-1), 1)){
                        velocityX = 0;
                        velocityY = velocityY*(-1);
                        go = 1;
                    }
                    else if( randomDirection % 3 == 1 && !colliding(1, 0, 1)){
                        velocityX = 1;
                        velocityY = 0;
                        go = 1;
                    }
                    else if( randomDirection == 5 && !colliding(-1, 0, 1)){
                        velocityX = -1;
                        velocityY = 0;
                        go = 1;
                    }
                }
            }
            else{
                while(go != 1){

                    randomDirection = random.nextInt(6);
                    if( randomDirection % 3 == 0 && !colliding(velocityX*(-1), 0, 1)){
                        velocityX = velocityX*(-1);
                        velocityY = 0;
                        go = 1;
                    }
                    else if( randomDirection % 3 == 1 && !colliding(0, 1, 1)){
                        velocityX = 0;
                        velocityY = 1;
                        go = 1;
                    }
                    else if( randomDirection == 5 && !colliding(0, -1, 1)){
                        velocityX = 0;
                        velocityY = -1;
                        go = 1;
                    }
                }
            }
            

        }
        else if(!colliding(velocityX, velocityY, 1)){
            int go = 0;
            if(velocityX==0){
                while(go != 1){

                    randomDirection = random.nextInt(6);
                    if( randomDirection == 0 && !colliding(1, 0, 1)){
                        velocityX = 1;
                        velocityY = 0;
                        go = 1;
                    }
                    else if( randomDirection == 1 && !colliding(-1, 0, 1)){
                        velocityX = -1;
                        velocityY = 0;
                        go = 1;
                    }
                    else if( randomDirection > 2 && !colliding(0, velocityY, 1)){
                        go = 1;
                    }
                }
            }
            else{
                while(go != 1){

                    randomDirection = random.nextInt(6);
                    if( randomDirection == 0 && !colliding(0, 1, 1)){
                        velocityX = 0;
                        velocityY = 1;
                        go = 1;
                    }
                    else if( randomDirection == 1 && !colliding(0, -1, 1)){
                        velocityX = 0;
                        velocityY = -1;
                        go = 1;
                    }
                    else if( randomDirection > 2 && !colliding(velocityX, 0, 1)){
                        go = 1;
                    }
                }
            }
        }
        x += velocityX;
        y += velocityY;
    }
   
    

    public boolean colliding(int velocityX, int velocityY, int num){
        if(mapProperties.map1[28*(y+velocityY)+x+velocityX]==num){
            return true;
        }
        return false;
    }

    public Point returnXY(){
        Point point = new Point(x, y);
        return point;
    }
}
