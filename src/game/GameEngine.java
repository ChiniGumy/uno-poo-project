package game;

import game.card.Card;
import game.card.SpecialCard;
import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import java.util.ArrayList;
import java.util.List;

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

    public static String currentColor;
    public static int round;
    
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

    public void remakeDeck() {
        ui.showRemakeDeck();
        for (int i = 0; i < cardHeap.getPlayedCardsSize() - 1; i++) {
            deck.getCards().add(cardHeap.getPlayedCards().removeFirst());   
        }
        deck.shuffleDeck();
    }

    public boolean isHandPlayable(ArrayList<Card> hand, Card lastPlayedCard) {
        
        boolean handPlayable = false;

        List<Card> playable = hand.stream()
            .filter(card -> card.isPlayable(lastPlayedCard, GameEngine.currentColor))
            .toList();
        
        for (Card card : playable) {
            handPlayable = card.isPlayable(lastPlayedCard, GameEngine.currentColor);
        }
        return handPlayable;
    }

    public void playRound(Player currentPlayer, Player opponent, int round, Deck deck) {
        Card topCard = cardHeap.getLastCardPlayed();

        if (round == 1){
            GameEngine.currentColor = topCard.getColor();
        }

        ui.clearScreen();
        ui.showRound(player, bot, cardHeap, deck, round);

        if (currentPlayer instanceof BotPlayer) {
            Card played = currentPlayer.playTurn(topCard, opponent, deck);
            if (played != null) {
                cardHeap.addCardToHeap(played);
                ui.showBotPlay(currentPlayer.toString(), played);
            } else {
                currentPlayer.drawCard(deck);
                ui.showDrawCard(currentPlayer.toString());
            }

            player.waitForUser();

        } else if (currentPlayer instanceof HumanPlayer) {
            if (isHandPlayable(player.getHand(), topCard)) {
                Card played = currentPlayer.playTurn(topCard, opponent, deck);
                cardHeap.addCardToHeap(played);
                ui.showHumanPlay(currentPlayer.toString(), played);

            } else {
                currentPlayer.drawCard(deck);
                ui.showDrawCard(currentPlayer.toString());
            }
            player.waitForUser();
        }
    }
    
    public void startGame() {
        round = 1;
        while (true) {
            if (deck.getRemainingCards() == 0) {
                ui.clearScreen();
                remakeDeck();
                player.waitForUser();
            }
            Player currentPlayer = (round % 2 == 0) ? bot : player;
            Player opponent = (round % 2 == 0) ? player : bot;
            playRound(currentPlayer, opponent, round, deck);
            if (player.getHand().isEmpty()) {
                System.out.println(UI.ANSI_GREEN + "¡Jugador ha ganado!" + UI.ANSI_RESET);
                break;
            } else if (bot.getHand().isEmpty()) {
                System.out.println(UI.ANSI_RED + "¡Bot ha ganado!" + UI.ANSI_RESET);
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
