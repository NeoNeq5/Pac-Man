import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int BoardWidth = 560;
        int BoardHeight = 720;

        JFrame frame = new JFrame("Pac-Man");
        PacmanGame Game = new PacmanGame(BoardHeight, BoardWidth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BoardWidth, BoardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(Game);
        frame.pack();
        Game.requestFocus();
        

    }
}
