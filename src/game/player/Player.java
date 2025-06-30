package game.player;

import game.Deck;
import game.card.Card;
import java.util.ArrayList;

public class Player {

    protected ArrayList<Card> hand = new ArrayList<>();

    public void drawCard(Deck deck){
        hand.add(deck.getCards().removeLast());
    }

    // public void playCard(Card card, CardHeap cardHeap){
    //     if (GameEngine.isPlayable(card, cardHeap)) {
    //         hand.remove(card);
    //         cardHeap.addCardtoHeap(card);
    //     }
    // }

    public ArrayList<Card> getHand() {
        return hand;
    }
}