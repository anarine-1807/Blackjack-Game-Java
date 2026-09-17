# Blackjack-Game-Java
Final project for Programming 2, built by a team of three. Implements standard casino Blackjack: players bet with poker chips, get dealt two cards, then hit or stand against a dealer that auto-draws to 17. Handles wins, losses, pushes, blackjacks, and a persistent balance across rounds.

Features
Betting system — place bets using $1, $10, $100, and $500 chips before each round
Hit / Stand gameplay — standard player turn logic with automatic bust detection
Dealer AI — dealer draws automatically until reaching a score of 17 or higher
Hidden hole card — dealer's first card is dealt face-down and only revealed at the appropriate moment (player stands, busts, or hits blackjack), matching real casino play
Dynamic Ace scoring — hands automatically recalculate Aces as 1 or 11 to avoid busting when possible
Custom-painted UI — hand-drawn card faces, chip buttons, and a felt-table background, all rendered with Java2D (Graphics2D)
Auto-reshuffling deck — the deck reshuffles automatically when it runs out of cards, so play can continue indefinitely
Persistent balance — player balance carries over between rounds, with win/loss/push payouts applied automatically. 


My Contributions

I was responsible for the core game-model layer that underpins all gameplay logic:

Card — represented an individual card's suit, rank, value, and face-up/down state
Deck — built the standard 52-card deck, handled shuffling, dealing, and automatic reshuffling when empty
Hand — calculated hand totals with dynamic Ace scoring (1 or 11) to keep hands from busting unnecessarily
Player — managed player balance, bet placement, and win/loss/push payout logic
Dealer — implemented the automatic "draw until 17" dealer AI, along with the hole-card hide/reveal mechanic for realistic dealer play

My teammate built the GUI layer (GamePanel, ControlPanel, BlackJackFrame) and the game controller (BlackJackGame) that ties the model and view together.

Built With
Java (JDK 8+)
Java Swing / Java2D (javax.swing, java.awt)

Team And Contributions:

Arjun Narine (Me): Game logic (Card, Deck, Hand, Player, Dealer)

Lourd Hadweh: GUI layer (GamePanel, ControlPanel, BlackJackFrame) and the game controller (BlackJackGame)

