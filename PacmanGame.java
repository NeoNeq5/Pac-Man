import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PacmanGame extends JPanel implements KeyListener,ActionListener{
    int BoardHeight;
    int BoardWidth;
    int tileSize = 20;

    // movement

    // PacMan
    PacMan pacMan;

    //Ghosts
    Ghost ghost0;
    Ghost ghost1;
    Ghost ghost2;
    Ghost ghost3;

    //obraz
    Image image;
    Image image1;
    ImageIcon imageIcon;
    
    // mapa
    MapProperties mapProperties;

    // logic
    Timer gameLoop;
    int t = 20;
    int j = 3;
    
    PacmanGame(int BoardHeight, int BoardWidth){
        this.BoardHeight = BoardHeight;
        this.BoardWidth = BoardWidth;
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(BoardWidth, BoardHeight));
        
        //images
        //images
        image = new ImageIcon("src/img/pacman_layout1.png").getImage();
        image1 = new ImageIcon("src/img/gameOver.png").getImage();
        
        // map properties;
        mapProperties = new MapProperties();
        

        //Ghost objects
        ghost0 = new Ghost(13,13,Color.WHITE, mapProperties,0,mapProperties.ghost1Pathx,mapProperties.ghost1Pathy);
        ghost1 = new Ghost(12,16,Color.green, mapProperties,20,mapProperties.ghost1Pathx,mapProperties.ghost1Pathy);
        ghost2 = new Ghost(13,16,Color.red, mapProperties,30,mapProperties.ghost2Pathx,mapProperties.ghost2Pathy);
        ghost3 = new Ghost(14,16,Color.BLUE, mapProperties,40,mapProperties.ghost3Pathx,mapProperties.ghost3Pathy);

        //PacMan object
        pacMan = new PacMan(BoardHeight,BoardWidth,mapProperties);

        //game loop
        gameLoop = new Timer(80, this);
        gameLoop.start();
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0, BoardWidth, BoardHeight, this);
        draw(g);

    }

    public void draw(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.orange);
        //points
        g.drawString(String.valueOf(pacMan.points*10),4,15);
        //lives 
        g.setColor(Color.yellow);
        for(int i = 0 ; i < j ; i++){
            g.fillArc(10+i*30,33*tileSize+10,24,24, 45, 270);
        }
        
        //game
        //


        //pacman
        if(j>0){
            if(pacMan.count%2==0){
                pacMan.draw(g);
                ghost0.draw(g);
                ghost1.draw(g);
                ghost2.draw(g);
                ghost3.draw(g);
            }
            else{
                pacMan.draw1(g);
                ghost0.draw1(g);
                ghost1.draw1(g);
                ghost2.draw1(g);
                ghost3.draw1(g);
            }  
        }
        else{
            g.drawImage(image1,0,0,BoardWidth,BoardHeight,this);
            j--;
        }
    }
        
    public void pacManDie(){
        pacMan.move(ghost0.returnXY(),ghost1.returnXY(),ghost2.returnXY(),ghost3.returnXY());
        if(pacMan.died == true){
            t = 0;
            pacMan.died = false;
            j--;
        }
        else if(t<20){
            pacMan.touchable = false;
            t++;
            pacMan.color1 = Color.WHITE;
        }
        else{
            pacMan.touchable = true;
            pacMan.color1 = Color.YELLOW;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        pacManDie();
        repaint();
        if(j==-1){
            gameLoop.stop();
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
                pacMan.velocityXT = 0;
                pacMan.velocityYT = -1;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
                pacMan.velocityXT = 0;
                pacMan.velocityYT = 1;                
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){

                pacMan.velocityXT = 1;
                pacMan.velocityYT = 0;               
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
                pacMan.velocityXT = -1;
                pacMan.velocityYT = 0;                

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }



    
}
