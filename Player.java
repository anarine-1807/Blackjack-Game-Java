public class Player {
    private Hand hand;
    private String name;
    private int balance;
    private int currentBet;

    public Player(String name, int startingBalance) {
        this.name = name;
        this.hand = new Hand();
        this.balance = startingBalance;
        this.currentBet = 0;
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return hand.calculateTotal();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean placeBet(int amount) {
        if (amount > 0 && amount <= balance) {
            currentBet += amount;
            balance -= amount;
            return true;
        }
        return false;
    }

    public void winBet() {
        balance += currentBet * 2;
        currentBet = 0;
    }

    public void pushBet() {
        balance += currentBet;
        currentBet = 0;
    }

    public void loseBet() {
        currentBet = 0;
    }

    public void resetBet() {
        currentBet = 0;
    }
}
