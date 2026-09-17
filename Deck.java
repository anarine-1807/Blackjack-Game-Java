import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        resetDeck();
    }

    public void resetDeck() {
        cards.clear();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values =   { 2,   3,   4,   5,   6,   7,   8,   9,   10,    10,      10,     10,    11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (isEmpty()) {
            resetDeck();
            shuffleDeck();
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
