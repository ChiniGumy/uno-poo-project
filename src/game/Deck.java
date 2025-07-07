package game;

import game.card.Card;
import game.card.NormalCard;
import game.card.SpecialCard;
import java.util.ArrayList;
import java.util.Collections;

public final class Deck {
    
    public Deck() {
        createDeck();
        shuffleDeck();
    }

    String[] colors = Card.getColors();
    String[] effects = SpecialCard.getEffects();
    String[] blackEffects = SpecialCard.getBlackeffects();

    ArrayList<Card> cards = new ArrayList<>();

    private void createDeck() {
        // Add normal cards to deck
        for (String color : colors) {
            for (int i = 0; i <= 0; i++) {
                NormalCard card = new NormalCard(i, color);
                cards.add(card);
            }
            
        }

        // Add special color cards to deck
        for (String color : colors) {
            for (String effect : effects) {
                for (int i = 0; i < 1; i++) {
                    SpecialCard card = new SpecialCard(effect, color);
                    cards.add(card);
                }
            }
        }

        // Add special black cards to deck
        for (String effect : blackEffects) {
            for (int i = 0; i < 5; i++) {
                SpecialCard card = new SpecialCard(effect);
                cards.add(card);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public int getRemainingCards() {
        return cards.size();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

}