import java.awt.Color;
import java.awt.Graphics;


public class Map {

    private int BoardHeight;
    private int BoardWidth;
    int tileSize = 20;
    int[] candyMap;
    int candyX;
    int candyY;
    

    Map(int BoardHeight, int BoardWidth, int[] candyMap){
        this.BoardHeight = BoardHeight;
        this.BoardWidth = BoardWidth;
        this.candyMap = candyMap;
    }

    public void drawMap(Graphics g){
        g.setColor(Color.gray);
        for(int i = 0; i < BoardHeight/tileSize; i++){
            //g.drawLine(0, i*tileSize, BoardWidth, i * tileSize);
        }
        for(int i = 0; i < BoardWidth/tileSize; i++){
            //g.drawLine(i*tileSize, 0, i * tileSize, BoardHeight);
        }
        g.setColor(Color.ORANGE);
        for(int i = 0; i < (BoardHeight/tileSize) * (BoardWidth/tileSize); i++){
            candyX = i-(i - i%28);
            candyY = i/28;
            if(candyMap[i]==0){
                
                g.fillRect(candyX * tileSize + 8, candyY * tileSize + 8, 4, 4);
            }
        }
        
    }
    
}
