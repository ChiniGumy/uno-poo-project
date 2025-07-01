package game;

import game.card.Card;
import java.util.ArrayList;

public class CardHeap {
    private final ArrayList<Card> playedCards = new ArrayList<>();

    public void addCardToHeap(Card card) {
        playedCards.add(card);
    }

    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }

    public int getPlayedCardsSize() {
        return playedCards.size();
    }

    public Card getLastCardPlayed() {
        return playedCards.get(playedCards.size() - 1);
    }
}
