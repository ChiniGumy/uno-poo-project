package game;

import game.card.Card;
import game.card.SpecialCard;
import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;

public final class GameEngine {

    final int HAND_SIZE = 7;

    public GameEngine() {
        dealFirstHand(player, bot);
        ui.showStartup();
    }

    private final Deck deck = new Deck();
    private final HumanPlayer player = new HumanPlayer();
    private final BotPlayer bot = new BotPlayer();
    private final CardHeap cardHeap = new CardHeap();
    private final UI ui = new UI();
    
    public void dealFirstHand(Player player, BotPlayer bot){
        for (int i = 0; i < HAND_SIZE; i++) {
            player.drawCard(deck);
        }

        for (int i = 0; i < HAND_SIZE; i++) {
            bot.drawCard(deck);
        }

        // Remove first card if it's special
        Card lastCardOnHeap;
        do {
            cardHeap.addCardToHeap(deck.getCards().removeLast());
            lastCardOnHeap = cardHeap.getLastCardPlayed(); 

            if (lastCardOnHeap instanceof SpecialCard){
                cardHeap.getPlayedCards().removeLast();
                deck.getCards().add(lastCardOnHeap);
                deck.shuffleDeck();
            }
        } while (lastCardOnHeap instanceof SpecialCard);
    }

    public void playRound(int round) {
        ui.showRound(player, bot, cardHeap, deck, round);

        Player currentPlayer = (round % 2 == 0) ? bot : player;
        Card topCard = cardHeap.getLastCardPlayed();

        if (currentPlayer instanceof BotPlayer) {
            Card played = currentPlayer.playTurn(topCard);
            // TODO checkear mano primero para ver si tiene cartas jugables
            if (played != null) {
                cardHeap.addCardToHeap(played);
                ui.showBotPlay(currentPlayer.toString(), played);
            } else {
                currentPlayer.drawCard(deck);
                ui.showDrawCard(currentPlayer.toString());
            }

        } else if (currentPlayer instanceof HumanPlayer) {

            Card played = currentPlayer.playTurn(topCard);

            if (played != null && played.isPlayable(topCard)) {
                cardHeap.addCardToHeap(played);
                ui.showHumanPlay(currentPlayer.toString(), played);
                
            } else {
                currentPlayer.drawCard(deck);
                ui.showDrawCard(currentPlayer.toString());
            }
            
            // TODO checkear mano primero para ver si tiene cartas jugables
            
        }

    }
    
    public void startGame() {
        int round = 1;
        while (true) {
            playRound(round);
            if (player.getHand().isEmpty()) {
                System.out.println("¡Jugador ha ganado!");
                break;
            } else if (bot.getHand().isEmpty()) {
                System.out.println("¡Bot ha ganado!");
                break;
            }
            round++;
        }
    }

    public HumanPlayer getPlayer() {
        return player;
    }

    public BotPlayer getBot() {
        return bot;
    }

    public Deck getDeck() {
        return deck;
    }

    public CardHeap getCardHeap() {
        return cardHeap;
    }
}
