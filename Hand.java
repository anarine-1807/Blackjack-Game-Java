import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int calculateTotal() {
        int total = 0;
        int aceCount = 0;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            total += card.getValue();
            if (card.getRank().equals("Ace")) {
                aceCount++;
            }
        }

        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    public void clearHand() {
        cards.clear();
    }

    public String toString() {
        if (cards.isEmpty()) {
            return "No cards";
        }

        String result = cards.get(0).toString();
        for (int i = 1; i < cards.size(); i++) {
            result = result + ",  " + cards.get(i).toString();
        }
        return result;
    }
}



