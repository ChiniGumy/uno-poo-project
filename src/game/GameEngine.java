package game;

import game.card.Card;
import game.card.SpecialCard;
import game.player.BotPlayer;
import game.player.HumanPlayer;
import game.player.Player;

public final class GameEngine {

    final int HandSize = 7;

    public GameEngine() {
        dealFirstHand(player, bot);
    }

    private final Deck deck = new Deck();
    private final HumanPlayer player = new HumanPlayer();
    private final BotPlayer bot = new BotPlayer();
    private final CardHeap cardHeap = new CardHeap();
    private final UI ui = new UI();
    
    public void dealFirstHand(Player player, BotPlayer bot){
        for (int i = 0; i < HandSize; i++) {
            player.drawCard(deck);
        }

        for (int i = 0; i < HandSize; i++) {
            bot.drawCard(deck);
        }

        // Remove first card if it's special
        Card lastCardOnHeap;
        do {
            cardHeap.addCardtoHeap(deck.getCards().removeLast());
            lastCardOnHeap = cardHeap.getLastCardPlayed(); 

            if (lastCardOnHeap instanceof SpecialCard){
                cardHeap.getPlayedCards().removeLast();
                deck.getCards().add(lastCardOnHeap);
                deck.shufleDeck();
            }
        } while (lastCardOnHeap instanceof SpecialCard);

        ui.showRound(player, bot, cardHeap, deck);
    }

    // static public boolean isPlayable(Card card, CardHeap cardHeap) {
    //     Card lastCardPlayed = cardHeap.getLastCardPlayed();

    //     if (card.getColor().equals(lastCardPlayed.getColor())) {
    //         return true;

    //     } else if (card instanceof NormalCard && lastCardPlayed instanceof NormalCard) {
    //         NormalCard card1 = (NormalCard) card;
    //         NormalCard card2 = (NormalCard) lastCardPlayed;
    //         return card1.getDenomination() == card2.getDenomination();

    //     } 
    //     else return card.getColor().equals("n");
    // }

    // public void showPlayedCard(){
    //     System.out.println(cardHeap.getLastCardPlayed());
    // }

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
