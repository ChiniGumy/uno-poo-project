package game.player;

import game.Deck;
import game.card.Card;
import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Card> hand = new ArrayList<>();

    public void drawCard(Deck deck){
        hand.add(deck.getCards().removeLast());
        hand.sort((c1, c2) -> Integer.compare(colorOrder(c1.getColor()), colorOrder(c2.getColor())));
    }

    private int colorOrder(String color) {
        return switch (color) {
            case "z" -> 0;          // blue
            case "a" -> 1;          // yellow
            case "r" -> 2;          // red
            case "v" -> 3;          // green
            case "n" -> 4;          // black
            default -> 5;
        };
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public abstract Card playTurn(Card topCard);

    protected void removeCard(int index) {
        hand.remove(index);
        hand.sort((c1, c2) -> Integer.compare(colorOrder(c1.getColor()), colorOrder(c2.getColor())));
    }
}