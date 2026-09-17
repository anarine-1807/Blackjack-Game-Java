import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel {

    JButton CardsPlayer;
    JButton CardsDealer;
    JButton PlayerScore;
    JButton DealerScore;
    JButton ResultMessage;
    Image backgroundImage;

    // NEW: card lists for painting
    private ArrayList<Card> playerCards = new ArrayList<>();
    private ArrayList<Card> dealerCards = new ArrayList<>();

    private static final int CARD_W   = 80;
    private static final int CARD_H   = 112;
    private static final int CARD_GAP = 18;
    private static final int CARD_ARC = 10;

    public GamePanel() {

        
        try {
            backgroundImage = new ImageIcon(
                    getClass().getResource("/BlackJackBackground.png")).getImage();
        } catch (Exception e1) {
            try {
                backgroundImage = new ImageIcon("BlackJackBackground.png").getImage();
            } catch (Exception e2) {
                backgroundImage = null;
            }
        }

        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        CardsPlayer = createDisplayButton("— Player Hand —");
        add(CardsPlayer);

        CardsDealer = createDisplayButton("— Dealer Hand —");
        add(CardsDealer);

        PlayerScore = createScoreButton("Player Score");
        add(PlayerScore);

        DealerScore = createScoreButton("Dealer Score");
        add(DealerScore);

        ResultMessage = createResultButton("Place your bet to start!");
        add(ResultMessage);
    }

    private JButton createDisplayButton(String text) {
        return createDisplayBox(
                text,
                new Color(35, 35, 35, 180),
                Color.WHITE,
                20
        );
    }

    private JButton createScoreButton(String text) {
        return createDisplayBox(
                text,
                new Color(80, 55, 20, 190),
                new Color(255, 220, 90),
                24
        );
    }

    private JButton createResultButton(String text) {
        return createDisplayBox(
                text,
                new Color(20, 110, 55, 190),
                Color.WHITE,
                24
        );
    }

    private JButton createDisplayBox(String text, Color color, Color textColor, int fontSize) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int boxWidth = getWidth() - 300;
                int boxX = (getWidth() - boxWidth) / 2;

                g2.setColor(getBackground());
                g2.fillRoundRect(boxX, 8, boxWidth, getHeight() - 16, 35, 35);

                g2.setColor(new Color(0, 0, 0, 90));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(boxX, 8, boxWidth, getHeight() - 16, 35, 35);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setForeground(textColor);
        button.setBackground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFocusable(false);

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        if (backgroundImage != null) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.90f));
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2d.setColor(new Color(20, 100, 50));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        g2d.dispose();
    }

    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        drawHand((Graphics2D) g, dealerCards, CardsDealer);
        drawHand((Graphics2D) g, playerCards, CardsPlayer);
    }


    private void drawHand(Graphics2D g, ArrayList<Card> cards, JButton zone) {
        if (cards.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int n = cards.size();
        int totalWidth = n * CARD_W + (n - 1) * CARD_GAP;
        int zoneX = zone.getX();
        int zoneY = zone.getY();
        int zoneW = zone.getWidth();
        int zoneH = zone.getHeight();

        int startX = zoneX + (zoneW - totalWidth) / 2;
        int startY = zoneY + (zoneH - CARD_H) / 2;

        for (int i = 0; i < n; i++) {
            int x = startX + i * (CARD_W + CARD_GAP);
            drawCard(g2, cards.get(i), x, startY);
        }

        g2.dispose();
    }


    private void drawCard(Graphics2D g2, Card card, int x, int y) {
        // Drop shadow
        g2.setColor(new Color(0, 0, 0, 60));
        g2.fillRoundRect(x + 4, y + 4, CARD_W, CARD_H, CARD_ARC, CARD_ARC);

        if (card.isFaceDown()) {
            drawFaceDownCard(g2, x, y);
        } else {
            drawFaceUpCard(g2, card, x, y);
        }
    }

    private void drawFaceDownCard(Graphics2D g2, int x, int y) {
        g2.setColor(new Color(15, 40, 90));
        g2.fillRoundRect(x, y, CARD_W, CARD_H, CARD_ARC, CARD_ARC);

        g2.setColor(new Color(180, 160, 100));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(x, y, CARD_W, CARD_H, CARD_ARC, CARD_ARC);

        g2.setColor(new Color(100, 130, 200, 120));
        g2.setStroke(new BasicStroke(1f));
        g2.drawRoundRect(x + 5, y + 5, CARD_W - 10, CARD_H - 10, 6, 6);

        Shape oldClip = g2.getClip();
        g2.setClip(new java.awt.geom.RoundRectangle2D.Float(
                x + 6, y + 6, CARD_W - 12, CARD_H - 12, 4, 4));
        g2.setColor(new Color(30, 60, 120, 180));
        g2.setStroke(new BasicStroke(1.5f));
        for (int i = -CARD_H; i < CARD_W + CARD_H; i += 10) {
            g2.drawLine(x + i, y, x + i + CARD_H, y + CARD_H);
        }
        g2.setClip(oldClip);

        g2.setFont(new Font("Georgia", Font.BOLD, 28));
        g2.setColor(new Color(200, 220, 255, 180));
        FontMetrics fm = g2.getFontMetrics();
        String q = "?";
        g2.drawString(q,
                x + (CARD_W - fm.stringWidth(q)) / 2,
                y + (CARD_H + fm.getAscent()) / 2 - 4);
    }

    
    private void drawFaceUpCard(Graphics2D g2, Card card, int x, int y) {
        boolean isRed = card.getSuit().equals("Hearts") || card.getSuit().equals("Diamonds");
        Color suitColor = isRed ? new Color(200, 20, 30) : new Color(15, 15, 15);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, CARD_W, CARD_H, CARD_ARC, CARD_ARC);

        g2.setColor(new Color(180, 160, 100));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(x, y, CARD_W, CARD_H, CARD_ARC, CARD_ARC);

        String rankStr = shortRank(card.getRank());
        String suitStr = suitSymbol(card.getSuit());

        g2.setColor(suitColor);
        g2.setFont(new Font("Georgia", Font.BOLD, 14));
        g2.drawString(rankStr, x + 5, y + 16);
        g2.setFont(new Font("Serif", Font.PLAIN, 13));
        g2.drawString(suitStr, x + 5, y + 30);

     
        Graphics2D g2r = (Graphics2D) g2.create();
        g2r.rotate(Math.PI, x + CARD_W / 2.0, y + CARD_H / 2.0);
        g2r.setColor(suitColor);
        g2r.setFont(new Font("Georgia", Font.BOLD, 14));
        g2r.drawString(rankStr, x + 5, y + 16);
        g2r.setFont(new Font("Serif", Font.PLAIN, 13));
        g2r.drawString(suitStr, x + 5, y + 30);
        g2r.dispose();

       
        g2.setFont(new Font("Serif", Font.PLAIN, 30));
        FontMetrics fm = g2.getFontMetrics();
        g2.setColor(suitColor);
        g2.drawString(suitStr,
                x + (CARD_W  - fm.stringWidth(suitStr)) / 2,
                y + (CARD_H  + fm.getAscent()) / 2 - 8);
    }

 
    private String shortRank(String rank) {
        switch (rank) {
            case "Jack":  return "J";
            case "Queen": return "Q";
            case "King":  return "K";
            case "Ace":   return "A";
            default:      return rank;
        }
    }

    private String suitSymbol(String suit) {
        switch (suit) {
            case "Hearts":   return "\u2665";
            case "Diamonds": return "\u2666";
            case "Clubs":    return "\u2663";
            case "Spades":   return "\u2660";
            default:         return suit;
        }
    }

    public void updateGameDisplay(BlackJackGame game) {

    
         CardsDealer.setText(" Dealer Hand ");
        CardsPlayer.setText(" Player Hand ");
    

        
        PlayerScore.setText("Player: " + game.getPlayerScore());

        boolean holeHidden = !game.getDealerHand().getCards().isEmpty()
                && game.getDealerHand().getCards().get(0).isFaceDown();
        if (holeHidden) {
         
            int visibleScore = 0;
            java.util.List<Card> dc = game.getDealerHand().getCards();
            for (int i = 1; i < dc.size(); i++) {   // skip index 0 (hole card)
                visibleScore += dc.get(i).getValue();
            }
            DealerScore.setText("Dealer: " + visibleScore + " + ?");
        } else {
            DealerScore.setText("Dealer: " + game.getDealerScore());
        }

        ResultMessage.setText(game.getResultMessage());


        playerCards = new ArrayList<>(game.getPlayerHand().getCards());
        dealerCards  = new ArrayList<>(game.getDealerHand().getCards());
        repaint();
    }
}