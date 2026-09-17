import javax.swing.*;
import java.awt.*;

public class BlackJackFrame extends JFrame {

    public BlackJackFrame() {

        setTitle("Black Jack Game");
        setSize(1100, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

       
        BlackJackGame game = new BlackJackGame();
        gamePanel.updateGameDisplay(game);

        ControlPanel panel = new ControlPanel(game, gamePanel);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}