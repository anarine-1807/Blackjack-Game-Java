public class BlackJackGame {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private boolean gameOver;
    private boolean bettingPhase;
    private String resultMessage;

    public BlackJackGame() {
        deck = new Deck();
        deck.shuffleDeck();
        player = new Player("Player", 5000);
        dealer = new Dealer();
        gameOver = true;
        bettingPhase = true;
        resultMessage = "Place your bet to start!";
    }

    public boolean placeBet(int amount) {
        if (!bettingPhase) {
            return false;
        }
        boolean success = player.placeBet(amount);
        if (!success) {
            resultMessage = "Not enough balance!";
        } else {
            resultMessage = "Bet: $" + player.getCurrentBet() + "  |  Balance: $" + player.getBalance();
        }
        return success;
    }

    public void startNewGame() {
        if (player.getCurrentBet() == 0) {
            resultMessage = "Place a bet first!";
            return;
        }

        deck.resetDeck();
        deck.shuffleDeck();
        player.getHand().clearHand();
        dealer.getHand().clearHand();
        gameOver = false;
        bettingPhase = false;
        resultMessage = "";

        player.hit(deck.dealCard());
        dealer.getHand().addCard(deck.dealCard());
        player.hit(deck.dealCard());
        dealer.getHand().addCard(deck.dealCard());

        // ── NEW: hide the dealer's first card (hole card) ────────
        dealer.hideHoleCard();

        if (player.getScore() == 21) {
            // Reveal hole card immediately on blackjack
            dealer.revealHoleCard();
            resultMessage = "Blackjack! You win $" + (player.getCurrentBet() * 2) + "!";
            player.winBet();
            gameOver = true;
            bettingPhase = true;
        }
    }

    public void playerHit() {
        if (!gameOver && !bettingPhase) {
            player.hit(deck.dealCard());
            if (player.getScore() > 21) {
                // ── NEW: reveal hole card when player busts ───────
                dealer.revealHoleCard();
                resultMessage = "You busted! You lost $" + player.getCurrentBet() + ".";
                player.loseBet();
                gameOver = true;
                bettingPhase = true;
            } else if (player.getScore() == 21) {
                playerStand();
            }
        }
    }

    public void playerStand() {
        if (!gameOver && !bettingPhase) {
            // ── NEW: reveal hole card before dealer draws ─────────
            dealer.revealHoleCard();
            dealerTurn();
            checkWinner();
            gameOver = true;
            bettingPhase = true;
        }
    }

    public void dealerTurn() {
        dealer.dealerDraw(deck);
    }

    public void checkWinner() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (dealerScore > 21) {
            resultMessage = "Dealer busted! You win $" + (player.getCurrentBet() * 2) + "!";
            player.winBet();
        } else if (playerScore > dealerScore) {
            resultMessage = "You win $" + (player.getCurrentBet() * 2) + "!";
            player.winBet();
        } else if (dealerScore > playerScore) {
            resultMessage = "Dealer wins. You lost $" + player.getCurrentBet() + ".";
            player.loseBet();
        } else {
            resultMessage = "It's a tie! Bet returned.";
            player.pushBet();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isBettingPhase() {
        return bettingPhase;
    }

    public Hand getPlayerHand() {
        return player.getHand();
    }

    public Hand getDealerHand() {
        return dealer.getHand();
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public int getPlayerScore() {
        return player.getScore();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public int getPlayerBalance() {
        return player.getBalance();
    }

    public int getCurrentBet() {
        return player.getCurrentBet();
    }
}