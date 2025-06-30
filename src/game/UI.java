package game;
import game.player.Player;

public class UI {
    public void showHand(Player player) {
        System.out.println("Mano de " + player + ": " + player.getHand());
    }

    public void showLastPlayedCard(CardHeap cardHeap) {
        System.out.println("Ultima carta jugada: " + cardHeap.getLastCardPlayed());
    }

    public void showRemainingDeckCards(Deck deck) {
        System.out.println("Cartas en el mazo: " + deck.getRemainingCards());
    }
    
    public void showCardHeapSize(CardHeap cardHeap) {
        System.out.println("Cartas en monte: " + cardHeap.getPlayedCardsSize());
    }

    public void showRound(Player player, Player bot, CardHeap cardHeap, Deck deck) {
        showRemainingDeckCards(deck);
        showCardHeapSize(cardHeap);
        showLastPlayedCard(cardHeap);
        showHand(player);
        showHand(bot);
    }
}
