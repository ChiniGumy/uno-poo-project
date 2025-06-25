package game;

import game.card.Card;
import game.card.NormalCard;
import game.card.SpecialCard;
import java.util.ArrayList;

public class Deck {
    
    public Deck() {
        createDeck();
    }

    String[] colors = Card.getColors();
    String[] effects = SpecialCard.getEffects();
    String[] blackEffects = SpecialCard.getBlackeffects();

    ArrayList<Card> deck = new ArrayList<>();

    private void createDeck() {
        // Add normal cards to deck
        for (String color : colors) {
            for (int i = 0; i <= 9; i++) {
                NormalCard card = new NormalCard(i, color);
                deck.add(card);
            }
            
        }

        // Add special color cards to deck
        for (String color : colors) {
            for (String effect : effects) {
                for (int i = 0; i < 2; i++) {
                    SpecialCard card = new SpecialCard(effect, color);
                    deck.add(card);
                }
            }
        }

        // Add special black cards to deck
        for (String effect : blackEffects) {
            for (int i = 0; i < 2; i++) {
                SpecialCard card = new SpecialCard(effect);
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

}