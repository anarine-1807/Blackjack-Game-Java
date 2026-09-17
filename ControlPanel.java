import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

   
    BlackJackGame game;
    GamePanel gamePanel;
    JButton hit;
    JButton stand;
    JButton newGame;
    JButton chip1;
    JButton chip10;
    JButton chip100;
    JButton chip500;

   
    public ControlPanel(BlackJackGame game, GamePanel gamePanel) {

        this.game = game;
        this.gamePanel = gamePanel;

        setLayout(new BorderLayout());
        setBackground(new Color(235, 235, 235));

        JPanel betPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        betPanel.setOpaque(false);

        chip1 = createChipButton("1", new Color(218, 165, 32), Color.WHITE);
        chip10 = createChipButton("10", new Color(35, 80, 200), Color.WHITE);
        chip100 = createChipButton("100", Color.BLACK, Color.WHITE);
        chip500 = createChipButton("500", new Color(70, 170, 225), Color.WHITE);

        betPanel.add(chip1);
        betPanel.add(chip10);
        betPanel.add(chip100);
        betPanel.add(chip500);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 12));
        buttonPanel.setOpaque(false);

        hit = createCircleButton("Hit", new Color(180, 0, 0));
        stand = createCircleButton("Stand", new Color(0, 60, 200));
        newGame = createRoundedButton("New Game", new Color(30, 130, 70));

        buttonPanel.add(hit);
        buttonPanel.add(stand);
        buttonPanel.add(newGame);

        add(betPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);

        
        chip1.addActionListener(e -> {
            game.placeBet(1);
            gamePanel.updateGameDisplay(game);
        });

        chip10.addActionListener(e -> {
            game.placeBet(10);
            gamePanel.updateGameDisplay(game);
        });

        chip100.addActionListener(e -> {
            game.placeBet(100);
            gamePanel.updateGameDisplay(game);
        });

        chip500.addActionListener(e -> {
            game.placeBet(500);
            gamePanel.updateGameDisplay(game);
        });

        hit.addActionListener(e -> {
            game.playerHit();
            gamePanel.updateGameDisplay(game);
        });

        stand.addActionListener(e -> {
            game.playerStand();
            gamePanel.updateGameDisplay(game);
        });

        newGame.addActionListener(e -> {
            game.startNewGame();
            gamePanel.updateGameDisplay(game);
        });
    }

    private JButton createCircleButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(255, 255, 255, 80));
                g2.fillOval(8, 6, getWidth() - 16, getHeight() / 2);

                g2.setColor(new Color(0, 0, 0, 80));
                g2.setStroke(new BasicStroke(3));
                g2.drawOval(2, 2, getWidth() - 5, getHeight() - 5);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(95, 95));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }

    private JButton createRoundedButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                g2.setColor(new Color(255, 255, 255, 70));
                g2.fillRoundRect(8, 5, getWidth() - 16, getHeight() / 2, 25, 25);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(160, 60));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }

    private JButton createChipButton(String text, Color color, Color textColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillOval(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(255, 255, 255, 90));
                g2.fillOval(8, 6, getWidth() - 16, getHeight() / 2);

                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(5));
                g2.drawOval(7, 7, getWidth() - 14, getHeight() - 14);

                g2.setColor(new Color(0, 0, 0, 90));
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(1, 1, getWidth() - 3, getHeight() - 3);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(80, 80));
        button.setForeground(textColor);
        button.setBackground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }
}