package game.player;

import game.Deck;
import game.card.Card;
import java.util.ArrayList;

public class Player {

    protected ArrayList<Card> hand = new ArrayList<>();

    public void drawCard(Deck deck){
        hand.add(deck.getCards().removeLast());
    }

    public ArrayList<Card> getHand() {
        System.out.println(hand);
        return hand;
    }
}