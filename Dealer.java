public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void dealerDraw(Deck deck) {
        while (getScore() < 17) {
            hand.addCard(deck.dealCard());
        }
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return hand.calculateTotal();
    }
     public void revealHoleCard() {
        if (!hand.getCards().isEmpty()) {
            hand.getCards().get(0).setFaceDown(false);
        }
    }
 
    public void hideHoleCard() {
        if (!hand.getCards().isEmpty()) {
            hand.getCards().get(0).setFaceDown(true);
        }
    }
}
 

